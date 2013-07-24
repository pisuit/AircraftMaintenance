package am.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import am.controller.AircraftController;
import am.controller.EngineController;
import am.controller.InspectionController;
import am.controller.PartsController;
import am.controller.PropellerController;
import am.customtype.CategoryType;
import am.customtype.DataStatus;
import am.customtype.DeviceType;
import am.customtype.InspectionType;
import am.model.Aircraft;
import am.model.Engine;
import am.model.Inspection;
import am.model.PartsCompability;
import am.model.Propeller;
import am.model.SpareParts;

@ManagedBean(name="partsManager")
@SessionScoped
public class PartsManager {
	private InspectionController inspectionController = new InspectionController();
	private AircraftController aircraftController = new AircraftController();
	private EngineController engineController = new EngineController();
	private PropellerController propellerController = new PropellerController();
	private PartsController partsController = new PartsController();
	private SpareParts editParts = new SpareParts();
	private SpareParts deletedParts = new SpareParts();
	private Long inspectionID = Long.valueOf(0);
	private ArrayList<SelectItem> inspectionSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> categorySelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SpareParts> partsList = new ArrayList<SpareParts>();
	private String inputFieldsetLegend = "Create Spare Parts";
	private String selectedCategory = "0";
	private ArrayList<SelectItem> aircraftItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> engineItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> propellerItemList = new ArrayList<SelectItem>();
	private List<String> selectedAircraftList;
	private List<String> selectedEngineList;
	private List<String> selectedPropellerList;
	private boolean isInspectionLocked = false;
	private ArrayList<SelectItem> filterOptions = new ArrayList<SelectItem>();
	private List<SpareParts> filtered;
	private ArrayList<SelectItem> inspectionTypeFilterOptions = new ArrayList<SelectItem>();
	
	
	
	public PartsManager(){
		createPartsList();
		createCategorySelectItemList();
		createInspectionSelectItemList();
		createAircraftSelectCheckboxList();
		createEngineSelectCheckboxList();
		createPropellerSelectCheckboxList();
//		createFilterOptions();
		createInspectionTypeFilterOptionsList();
	}
	
	private void createInspectionTypeFilterOptionsList(){
		if(inspectionTypeFilterOptions != null) inspectionTypeFilterOptions.clear();
		
		inspectionTypeFilterOptions.add(new SelectItem("","All"));
		for(InspectionType type : InspectionType.values()){
			inspectionTypeFilterOptions.add(new SelectItem(type.getValue(),type.getValue()));
		}
	}
	
	private void createFilterOptions(){
		if(filterOptions != null) filterOptions.clear();
		ArrayList<Inspection> inspectionList = inspectionController.getAllInspection();
		
		filterOptions.add(new SelectItem("", "All"));
		filterOptions.add(new SelectItem("null", "None"));
		for(Inspection inspection : inspectionList){
			filterOptions.add(new SelectItem(inspection.getName(), inspection.getName()));
		}
	}
	
	private void createInspectionSelectItemList(){
		if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
		ArrayList<Inspection> inspectionList = inspectionController.getAllInspection();
		
		inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "None"));
		for(Inspection inspection : inspectionList){
			inspectionSelectItemList.add(new SelectItem(inspection.getId(), inspection.getName()));
		}		
	}
	
	private void createCategorySelectItemList(){
		if(categorySelectItemList != null) categorySelectItemList.clear();
		
		categorySelectItemList.add(new SelectItem("0", "Select One"));
		for(CategoryType categoryType : CategoryType.values()){
			categorySelectItemList.add(new SelectItem(categoryType.getID(), categoryType.getValue()));
		}
	}
	
	private void createPartsList(){
		if(partsList != null) partsList.clear();		
		partsList = partsController.getAllParts();
	}
	
	public void saveParts(){
		ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();
		ArrayList<Engine> engineList = new ArrayList<Engine>();
		ArrayList<Propeller> propellerList = new ArrayList<Propeller>();
		
		for(String id : selectedAircraftList){
			aircraftList.add(aircraftController.getAircraft(Long.valueOf(id)));
		}		
		for(String id : selectedEngineList){
			engineList.add(engineController.getEngine(Long.valueOf(id)));
		}
		for(String id : selectedPropellerList){
			propellerList.add(propellerController.getPropeller(Long.valueOf(id)));
		}
		
		partsController.saveParts(editParts, aircraftList, engineList, propellerList);
		createPartsList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deleteParts(){
		deletedParts.setDataStatus(DataStatus.DELETED);
		partsController.saveParts(deletedParts, null, null, null);
		createPartsList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void clearData(){
		editParts = new SpareParts();
		deletedParts = new SpareParts();
		selectedCategory = "0";
		inputFieldsetLegend = "Create Spare Parts";
		selectedAircraftList = null;
		selectedEngineList = null;
		selectedPropellerList = null;
		
		if(isInspectionLocked == false){
			inspectionID = Long.valueOf(0);
		} else {
			Inspection inspection = inspectionController.getInspection(inspectionID);
			editParts.setInspection(inspection);
		}
	}
	
	public void createAircraftSelectCheckboxList(){
		if(aircraftItemList != null) aircraftItemList.clear();
		ArrayList<Aircraft> aircraftList = aircraftController.getAllAircraft();
		
		for(Aircraft aircraft : aircraftList){
			aircraftItemList.add(new SelectItem(String.valueOf(aircraft.getId()), aircraft.getRegister()));		
		}
	}
	
	public void createEngineSelectCheckboxList(){
		if(engineItemList != null) engineItemList.clear();
		ArrayList<Engine> engineList = engineController.getAllEngine();
		
		for(Engine engine : engineList){
			engineItemList.add(new SelectItem(String.valueOf(engine.getId()), engine.getUniqeName()));
		}
	}

	public void createPropellerSelectCheckboxList(){
		if(propellerItemList != null) propellerItemList.clear();
		ArrayList<Propeller> propellerList = propellerController.getAllPropeller();
		
		for(Propeller propeller : propellerList){
			propellerItemList.add(new SelectItem(String.valueOf(propeller.getId()), propeller.getUniqeName()));
		}
	}
	
	public void createAllList(){
		createPartsList();
		createCategorySelectItemList();
		createInspectionSelectItemList();
		createAircraftSelectCheckboxList();
		createEngineSelectCheckboxList();
		createPropellerSelectCheckboxList();
//		createFilterOptions();
	}
	
	public void setSelectedParts(){
		if(selectedAircraftList != null) selectedAircraftList.clear();
		if(selectedEngineList != null) selectedEngineList.clear();
		if(selectedPropellerList != null) selectedPropellerList.clear();
		
		inputFieldsetLegend = "Edit Spare Parts "+ "[" +editParts.getName()+ "]";
		if(editParts.getCategoryType() != null){
			selectedCategory = editParts.getCategoryType().getID();
		}
		if(editParts.getInspection() != null && editParts.getInspection().getId() != null){
			inspectionID = editParts.getInspection().getId();
			isInspectionLocked = false;
		} else {
			inspectionID = Long.valueOf(0);
			isInspectionLocked = false;
		}
		
		for(PartsCompability compability : editParts.getCompabilityList()){
			if(compability.getDeviceType().equals(DeviceType.AIRCRAFT)){
				selectedAircraftList.add(String.valueOf(compability.getAircraft().getId()));
			}
			if(compability.getDeviceType().equals(DeviceType.ENGINE)){
				selectedEngineList.add(String.valueOf(compability.getEngine().getId()));
			}
			if(compability.getDeviceType().equals(DeviceType.PROPELLER)){
				selectedPropellerList.add(String.valueOf(compability.getPropeller().getId()));
			}
		}
	}
	
	public void inspectionSelected(){
		Inspection inspection = inspectionController.getInspection(inspectionID);
		editParts.setInspection(inspection);
	}
	
	public void categorySelected(){
		editParts.setCategoryType(CategoryType.find(selectedCategory));
	}
	
	public SpareParts getEditParts() {
		return editParts;
	}
	public void setEditParts(SpareParts editParts) {
		this.editParts = editParts;
	}
	public SpareParts getDeletedParts() {
		return deletedParts;
	}
	public void setDeletedParts(SpareParts deletedParts) {
		this.deletedParts = deletedParts;
	}
	public ArrayList<SpareParts> getPartsList() {
		return partsList;
	}
	public void setPartsList(ArrayList<SpareParts> partsList) {
		this.partsList = partsList;
	}
	public List<String> getSelectedAircraftList() {
		return selectedAircraftList;
	}
	public void setSelectedAircraftList(List<String> selectedAircraftList) {
		this.selectedAircraftList = selectedAircraftList;
	}
	public boolean isInspectionLocked() {
		return isInspectionLocked;
	}
	public void setInspectionLocked(boolean isInspectionLocked) {
		this.isInspectionLocked = isInspectionLocked;
	}
	public List<String> getSelectedEngineList() {
		return selectedEngineList;
	}
	public void setSelectedEngineList(List<String> selectedEngineList) {
		this.selectedEngineList = selectedEngineList;
	}
	public ArrayList<SelectItem> getFilterOptions() {
		return filterOptions;
	}
	public void setFilterOptions(ArrayList<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}
	public List<String> getSelectedPropellerList() {
		return selectedPropellerList;
	}
	public void setSelectedPropellerList(List<String> selectedPropellerList) {
		this.selectedPropellerList = selectedPropellerList;
	}
	public ArrayList<SelectItem> getAircraftItemList() {
		return aircraftItemList;
	}
	public void setAircraftItemList(ArrayList<SelectItem> aircraftItemList) {
		this.aircraftItemList = aircraftItemList;
	}
	public ArrayList<SelectItem> getEngineItemList() {
		return engineItemList;
	}
	public void setEngineItemList(ArrayList<SelectItem> engineItemList) {
		this.engineItemList = engineItemList;
	}
	public ArrayList<SelectItem> getPropellerItemList() {
		return propellerItemList;
	}
	public void setPropellerItemList(ArrayList<SelectItem> propellerItemList) {
		this.propellerItemList = propellerItemList;
	}
	public Long getInspectionID() {
		return inspectionID;
	}
	public void setInspectionID(Long inspectionID) {
		this.inspectionID = inspectionID;
	}
	public ArrayList<SelectItem> getInspectionSelectItemList() {
		return inspectionSelectItemList;
	}
	public void setInspectionSelectItemList(
			ArrayList<SelectItem> inspectionSelectItemList) {
		this.inspectionSelectItemList = inspectionSelectItemList;
	}
	public ArrayList<SelectItem> getCategorySelectItemList() {
		return categorySelectItemList;
	}
	public void setCategorySelectItemList(
			ArrayList<SelectItem> categorySelectItemList) {
		this.categorySelectItemList = categorySelectItemList;
	}
	public String getInputFieldsetLegend() {
		return inputFieldsetLegend;
	}
	public void setInputFieldsetLegend(String inputFieldsetLegend) {
		this.inputFieldsetLegend = inputFieldsetLegend;
	}
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<SpareParts> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<SpareParts> filtered) {
		this.filtered = filtered;
	}

	public ArrayList<SelectItem> getInspectionTypeFilterOptions() {
		return inspectionTypeFilterOptions;
	}

	public void setInspectionTypeFilterOptions(
			ArrayList<SelectItem> inspectionTypeFilterOptions) {
		this.inspectionTypeFilterOptions = inspectionTypeFilterOptions;
	}
}
