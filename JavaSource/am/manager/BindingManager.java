package am.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.jboss.aop.Bind;
import org.joda.time.DateTime;
import org.primefaces.event.SelectEvent;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import am.controller.AircraftController;
import am.controller.BindingController;
import am.controller.ChapterController;
import am.controller.EngineController;
import am.controller.InspectionController;
import am.controller.PropellerController;
import am.customtype.DeviceType;
import am.customtype.InspectionType;
import am.datamodel.ChapterDataModel;
import am.model.Aircraft;
import am.model.Binding;
import am.model.Chapter;
import am.model.Engine;
import am.model.Inspection;
import am.model.Propeller;

@ManagedBean(name="bindingManager")
@SessionScoped
public class BindingManager {

	private Chapter selectedChapter;
	private Long chapterID = Long.valueOf(0);
	private ArrayList<SelectItem> deviceSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> chapterSelectItemList = new ArrayList<SelectItem>();
	private AircraftController aircraftController = new AircraftController();
	private ChapterController chapterController = new ChapterController();
	private BindingController bindingController = new BindingController();
	private EngineController engineController = new EngineController();
	private PropellerController propellerController = new PropellerController();
	private ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
	private InspectionController inspectionController = new InspectionController();
	private Binding editBinding = new Binding();
	private String selectedDeviceType;
	private Long deviceID = Long.valueOf(0);
	private Long inspectionID = Long.valueOf(0);
	private ArrayList<Binding> bindingList = new ArrayList<Binding>();
	private Aircraft selectedAircraft;
	private Engine selectedEngine;
	private Propeller selectedPropeller;
	private ArrayList<SelectItem> inspectionSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> filterOptions = new ArrayList<SelectItem>();
	private String legendText = "";
	private Inspection selectedInspection;
	private ArrayList<SelectItem> deviceTypeSelectItemList = new ArrayList<SelectItem>();
	private Binding deletedBinding = new Binding();
	private List<Binding> filtered;
	private ArrayList<SelectItem> inspectionTypeFilterOptions = new ArrayList<SelectItem>();
	
	
	
	public BindingManager(){
		createDeviceTypeSelectItemList();
		createInspectionTypeFilterOptionsList();
		createFilterOptions();
		deviceSelectItemList.add(new SelectItem(Long.valueOf(0),"Please select device type"));
		chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
		inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
	}
	
	public void createAllList(){
		createFilterOptions();
	}
	
	private void createInspectionTypeFilterOptionsList(){
		if(inspectionTypeFilterOptions != null) inspectionTypeFilterOptions.clear();
		
		inspectionTypeFilterOptions.add(new SelectItem("","All"));
		for(InspectionType type : InspectionType.values()){
			inspectionTypeFilterOptions.add(new SelectItem(type.getValue(),type.getValue()));
		}
	}
	
	private void createAircraftSelectItemList(){
		if(deviceSelectItemList != null) deviceSelectItemList.clear();
		
		ArrayList<Aircraft> aircraftList = aircraftController.getAllAircraft();
		deviceSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Aircraft aircraft : aircraftList){
			deviceSelectItemList.add(new SelectItem(aircraft.getId(), aircraft.getRegister()));
		}
	}
	
	private void createEngineSelectItemList(){
		if(deviceSelectItemList != null) deviceSelectItemList.clear();
		
		ArrayList<Engine> engineList = engineController.getAllEngine();
		deviceSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Engine engine : engineList){
			deviceSelectItemList.add(new SelectItem(engine.getId(), engine.getUniqeName()));
		}
	}
	
	private void createPropellerSelectItemList(){
		if(deviceSelectItemList != null) deviceSelectItemList.clear();
		
		ArrayList<Propeller> propelelrList = propellerController.getAllPropeller();
		deviceSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Propeller propeller : propelelrList){
			deviceSelectItemList.add(new SelectItem(propeller.getId(), propeller.getUniqeName()));
		}
	}
		
	private void createChapterSelectItemList(){
		if(chapterSelectItemList != null) chapterSelectItemList.clear();
		
		chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		ArrayList<Chapter> chapterList = chapterController.getAllChapter();
		for(Chapter chapter : chapterList){
			chapterSelectItemList.add(new SelectItem(chapter.getId(), chapter.getChapterName()));
		}
	}
	
	private void createInspectionSelectItemList(){
		if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
		
		inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		ArrayList<Inspection> inspectionList = inspectionController.getInspectionByChapter(selectedChapter);
		ArrayList<String> strBindingList = new ArrayList<String>();
		
		for(Binding binding : bindingList){
			strBindingList.add(binding.getInspection().getName());
		}
		
		for(Inspection inspection : inspectionList){	
			if(!strBindingList.contains(inspection.getName())){
				inspectionSelectItemList.add(new SelectItem(inspection.getId(), inspection.getName()));		
			}	
		}		
	}
	
	private void createDeviceTypeSelectItemList(){
		if(deviceTypeSelectItemList != null) deviceTypeSelectItemList.clear();
		deviceTypeSelectItemList.add(new SelectItem("selectone", "Select One"));
		for(DeviceType type : DeviceType.values()){
			if(!type.equals(DeviceType.NONE)){
				deviceTypeSelectItemList.add(new SelectItem(type.getID(), type.getValue()));
			}
		}
	}
	
	public void addChapter(){
		System.out.println("here we go");
		Binding binding;
		
		ArrayList<Inspection> inspections = inspectionController.getInspectionByChapter(selectedChapter);
		for(Inspection inspection : inspections){
			binding = new Binding();
		
			if(selectedDeviceType.equals(DeviceType.AIRCRAFT.getID())){
				binding.setAircraft(selectedAircraft);
				binding.setCurrentFlightHour(selectedAircraft.getCurrentFlightHour());
				binding.setCurrentLandingCycle(selectedAircraft.getCurrentLandingCycle());
				binding.setDeviceType(DeviceType.AIRCRAFT);
			} else if(selectedDeviceType.equals(DeviceType.ENGINE.getID())){
				binding.setEngine(selectedEngine);
				binding.setCurrentEngineCycle(selectedEngine.getCurrentEngineCycle());
				binding.setCurrentFlightHour(selectedEngine.getCurrentFlightHour());
				binding.setDeviceType(DeviceType.ENGINE);
			} else if(selectedDeviceType.equals(DeviceType.PROPELLER.getID())){
				binding.setPropeller(selectedPropeller);
				binding.setCurrentPropellerCycle(selectedPropeller.getCurrentPropellerCycle());
				binding.setCurrentFlightHour(selectedPropeller.getCurrentFlightHour());
				binding.setDeviceType(DeviceType.PROPELLER);
			}
			binding.setChapter(selectedChapter);
			binding.setInspection(inspection);
			bindingController.saveBinding(binding);
		}
		createBindingList(selectedDeviceType);
		createInspectionSelectItemList();
		chapterID = Long.valueOf(0);
		inspectionID = Long.valueOf(0);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void addInspection(){
		Binding binding = new Binding();
		
		if(selectedDeviceType.equals(DeviceType.AIRCRAFT.getID())){
			binding.setAircraft(selectedAircraft);
			binding.setCurrentLandingCycle(selectedAircraft.getCurrentLandingCycle());
			binding.setCurrentFlightHour(selectedAircraft.getCurrentFlightHour());
			binding.setDeviceType(DeviceType.AIRCRAFT);
		} else if(selectedDeviceType.equals(DeviceType.ENGINE.getID())){
			binding.setEngine(selectedEngine);
			binding.setCurrentEngineCycle(selectedEngine.getCurrentEngineCycle());
			binding.setCurrentFlightHour(selectedEngine.getCurrentFlightHour());
			binding.setDeviceType(DeviceType.ENGINE);
		} else if(selectedDeviceType.equals(DeviceType.PROPELLER.getID())){
			binding.setPropeller(selectedPropeller);
			binding.setCurrentPropellerCycle(selectedPropeller.getCurrentPropellerCycle());
			binding.setCurrentFlightHour(selectedPropeller.getCurrentFlightHour());
			binding.setDeviceType(DeviceType.PROPELLER);
		}
		binding.setChapter(selectedChapter);
		binding.setInspection(selectedInspection);
		bindingController.saveBinding(binding);
		
		createBindingList(selectedDeviceType);
		createInspectionSelectItemList();
		inspectionID = Long.valueOf(0);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void inspectionSelected(){
		selectedInspection = inspectionController.getInspection(inspectionID);
	}
	
	public void deviceTypeSelected(){
		if(deviceSelectItemList != null) deviceSelectItemList.clear();
		if(chapterSelectItemList != null) chapterSelectItemList.clear();
		if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
		if(bindingList != null) bindingList.clear();
//		if(filterOptions != null) filterOptions.clear();
		deviceID = Long.valueOf(0);
		
		if(selectedDeviceType.equals(DeviceType.AIRCRAFT.getID())){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
			createAircraftSelectItemList();
		} else if(selectedDeviceType.equals(DeviceType.ENGINE.getID())){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
			createEngineSelectItemList();
		} else if(selectedDeviceType.equals(DeviceType.PROPELLER.getID())) {
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
			createPropellerSelectItemList();
		} else {
			deviceSelectItemList.add(new SelectItem(Long.valueOf(0) ,"Please select device type"));
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
		}
	}
	
	public void deviceSelected(){
		if(chapterSelectItemList != null) chapterSelectItemList.clear();
		if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
		chapterID = Long.valueOf(0);
		
		if(selectedDeviceType.equals(DeviceType.AIRCRAFT.getID())){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			selectedAircraft = aircraftController.getAircraft(deviceID);
			createBindingList("aircraft");
			createChapterSelectItemList();
		} else if(selectedDeviceType.equals(DeviceType.ENGINE.getID())){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			selectedEngine = engineController.getEngine(deviceID);
			createBindingList("engine");
			createChapterSelectItemList();
		} else if(selectedDeviceType.equals(DeviceType.PROPELLER.getID())){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			selectedPropeller = propellerController.getPropeller(deviceID);
			createBindingList("propeller");
			createChapterSelectItemList();
		}
		if(deviceID.equals(Long.valueOf(0))){
			if(chapterSelectItemList != null) chapterSelectItemList.clear();
			if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
//			if(filterOptions != null) filterOptions.clear();
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
			chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select device"));
		}
	}
	
	public void chapterSelected(){
		if(inspectionSelectItemList != null) inspectionSelectItemList.clear();
		inspectionID = Long.valueOf(0);
		
		selectedChapter = chapterController.getChapter(chapterID);
		if(chapterID.equals(Long.valueOf(0))){
			inspectionSelectItemList.add(new SelectItem(Long.valueOf(0), "Please select chapter"));
		} else {
			createInspectionSelectItemList();
		}		
	}
	
	private void createBindingList(String type){
		if(bindingList != null) bindingList.clear();
		
		if(type.equalsIgnoreCase("aircraft")){
			bindingList = bindingController.getAircraftBinding(selectedAircraft);
		} else if(type.equalsIgnoreCase("engine")){
			bindingList = bindingController.getEngineBinding(selectedEngine);
		} else if(type.equalsIgnoreCase("propeller")){
			bindingList = bindingController.getPropellerBinding(selectedPropeller);
		}
	}
	
	private void createFilterOptions(){
//		if(filterOptions != null) filterOptions.clear();
//		String temp = "";
//		
//		if(bindingList.size() != 0) filterOptions.add(new SelectItem("", "All"));
//		for(Binding binding : bindingList){			
//			if(!temp.equals(binding.getChapter().toString())){
//				filterOptions.add(new SelectItem(binding.getChapter().toString(), binding.getChapter().toString()));
//			}	
//			temp = binding.getChapter().toString();
//		}
		if(filterOptions != null) filterOptions.clear();
		ArrayList<Chapter> chapterList = chapterController.getAllChapter();
		
		filterOptions.add(new SelectItem("", "All"));
		for(Chapter chapter : chapterList){
			filterOptions.add(new SelectItem(chapter.toString(), chapter.toString()));
		}
	}
	
	public void saveBinding(){
		bindingController.saveBinding(editBinding);
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deleteBinding(){
		bindingController.deleteBinding(deletedBinding);
		createBindingList(selectedDeviceType);
		createInspectionSelectItemList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void clearData(){
		editBinding = new Binding();
		deletedBinding = new Binding();
		legendText = "";
	}
	
	public void setSelectedBinding(){
		legendText = "["+editBinding.getInspection().getName()+"]";
	}
	
	public void calculateNextLandingCycle(ValueChangeEvent event){
		editBinding.setNextLandingCyccle((Integer)event.getNewValue() + editBinding.getInspection().getLimitedLandingCycle());
	}
	
	public void calculateNextEngineCycle(ValueChangeEvent event){
		editBinding.setNextEngineCycle((Integer)event.getNewValue() + editBinding.getInspection().getLimitedEngineCycle());
	}
	
	public void calculateNextPropellerCycle(ValueChangeEvent event){
		editBinding.setNextPropellerCycle((Integer)event.getNewValue() + editBinding.getInspection().getLimitedPropellerCycle());
	}
	
	public void calculateNextFlightHour(ValueChangeEvent event){
		editBinding.setNextFlightHour((Integer)event.getNewValue() + editBinding.getInspection().getLimitedFlightHour());
	}
	
	public void calculateNextDueDate(SelectEvent event){	
		DateTime previousDueDate = new DateTime((Date)event.getObject());
		editBinding.setNextDueDate(previousDueDate.plusMonths(editBinding.getInspection().getLimitedTime()).toDate());
//		DateTime previousDueDate = new DateTime(editBinding.getPreviousDueDate());
//		editBinding.setNextDueDate(previousDueDate.plusMonths(editBinding.getInspection().getLimitedTime()).toDate());
	}
	
	

	
	public Chapter getSelectedChapter() {
		return selectedChapter;
	}
	public void setSelectedChapter(Chapter selectedChapter) {
		this.selectedChapter = selectedChapter;
	}

	public String getLegendText() {
		return legendText;
	}
	public void setLegendText(String legendText) {
		this.legendText = legendText;
	}
	public ArrayList<SelectItem> getFilterOptions() {
		return filterOptions;
	}
	public void setFilterOptions(ArrayList<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}
	public Binding getDeletedBinding() {
		return deletedBinding;
	}
	public void setDeletedBinding(Binding deletedBinding) {
		this.deletedBinding = deletedBinding;
	}
	public ArrayList<SelectItem> getDeviceTypeSelectItemList() {
		return deviceTypeSelectItemList;
	}
	public void setDeviceTypeSelectItemList(
			ArrayList<SelectItem> deviceTypeSelectItemList) {
		this.deviceTypeSelectItemList = deviceTypeSelectItemList;
	}

	public String getSelectedDeviceType() {
		return selectedDeviceType;
	}
	public void setSelectedDeviceType(String selectedDeviceType) {
		this.selectedDeviceType = selectedDeviceType;
	}

	public Long getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(Long deviceID) {
		this.deviceID = deviceID;
	}

	public ArrayList<Binding> getBindingList() {
		return bindingList;
	}
	public void setBindingList(ArrayList<Binding> bindingList) {
		this.bindingList = bindingList;
	}
	public ArrayList<SelectItem> getInspectionSelectItemList() {
		return inspectionSelectItemList;
	}
	public void setInspectionSelectItemList(
			ArrayList<SelectItem> inspectionSelectItemList) {
		this.inspectionSelectItemList = inspectionSelectItemList;
	}
	public Long getInspectionID() {
		return inspectionID;
	}
	public void setInspectionID(Long inspectionID) {
		this.inspectionID = inspectionID;
	}
	
	public ArrayList<SelectItem> getChapterSelectItemList() {
		return chapterSelectItemList;
	}
	public void setChapterSelectItemList(ArrayList<SelectItem> chapterSelectItemList) {
		this.chapterSelectItemList = chapterSelectItemList;
	}
	public Long getChapterID() {
		return chapterID;
	}
	public void setChapterID(Long chapterID) {
		this.chapterID = chapterID;
	}
	public ArrayList<SelectItem> getDeviceSelectItemList() {
		return deviceSelectItemList;
	}
	public void setDeviceSelectItemList(ArrayList<SelectItem> deviceSelectItemList) {
		this.deviceSelectItemList = deviceSelectItemList;
	}
	public Binding getEditBinding() {
		return editBinding;
	}
	public void setEditBinding(Binding editBinding) {
		this.editBinding = editBinding;
	}
	public ArrayList<Chapter> getChapterList() {
		return chapterList;
	}
	public void setChapterList(ArrayList<Chapter> chapterList) {
		this.chapterList = chapterList;
	}

	public List<Binding> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Binding> filtered) {
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
