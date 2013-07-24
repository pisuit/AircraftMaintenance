package am.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.joda.time.DateTime;

import am.controller.AircraftController;
import am.controller.BindingController;
import am.controller.PartsController;
import am.controller.WorkCardController;
import am.customtype.DataStatus;
import am.customtype.InspectionType;
import am.customtype.MaintenanceType;
import am.customtype.WorkCardStatus;
import am.model.Aircraft;
import am.model.Binding;
import am.model.SpareParts;
import am.model.WorkCard;
import am.model.WorkCardInspection;
import am.model.WorkCardParts;

@ManagedBean(name="workCardManager")
@SessionScoped
public class WorkCardManager {
	private List<WorkCard> workCardList = new ArrayList<WorkCard>();
	private WorkCard editWorkCard = new WorkCard();
	private List<WorkCard> filtered;
	private WorkCardController workCardController = new WorkCardController();
	private ArrayList<SelectItem> aircraftSelectItemList = new ArrayList<SelectItem>();
	private AircraftController aircraftController = new AircraftController();
	private Long aircraftID = Long.valueOf(0);
	private Aircraft selectedAircraft;
	private ArrayList<SelectItem> maintenanceTypeSelectItemList = new ArrayList<SelectItem>();
	private String selectedMaintenanceType = MaintenanceType.CORRECTIVE.getID();
	private List<WorkCardParts> sparePartList = new ArrayList<WorkCardParts>();
	private List<SelectItem> partSelectItemList = new ArrayList<SelectItem>();
	private Long selectedPart = Long.valueOf(0);
	private PartsController partsController = new PartsController();
	private SpareParts editPart = new SpareParts();
	private String workcardFieldsetLegend = "Create Work Card";
	private WorkCard deletedWorkCard = new WorkCard();
	private WorkCard closedWorkCard = new WorkCard();
	private BindingController bindingController = new BindingController();
	private List<SelectItem> statusFilterOptions = new ArrayList<SelectItem>();
	private List<SelectItem> typeFilterOptions = new ArrayList<SelectItem>();
	private List<SelectItem> aircraftFilterOptions = new ArrayList<SelectItem>();
	
	public WorkCardManager(){
		createWorkCardList();
		createAircraftSelectItemList();
		createMaintenanceType();
		createPartSelectItemList();
		createStatusFilterOptions();
		createTypeFilterOption();
	}
	
	private void createStatusFilterOptions(){
		if(statusFilterOptions != null) statusFilterOptions.clear();
		
		statusFilterOptions.add(new SelectItem("", "All"));
		for(WorkCardStatus status : WorkCardStatus.values()){
			statusFilterOptions.add(new SelectItem(status.getValue(), status.getValue()));
		}
	}
	
	private void createTypeFilterOption(){
		if(typeFilterOptions != null) typeFilterOptions.clear();
		
		typeFilterOptions.add(new SelectItem("", "All"));
		for(MaintenanceType type : MaintenanceType.values()){
			typeFilterOptions.add(new SelectItem(type.getValue(), type.getValue()));
		}
	}
	
	public void clearData(){
		editWorkCard = new WorkCard();
		aircraftID = Long.valueOf(0);
		selectedAircraft = null;
		selectedMaintenanceType = MaintenanceType.CORRECTIVE.getID();
		sparePartList.clear();
		selectedPart = Long.valueOf(0);
		editPart = new SpareParts();
		workcardFieldsetLegend = "Create Work Card";
	}
	
	public void deleteWorkCard(){
		deletedWorkCard.setStatus(DataStatus.DELETED);
		workCardController.saveWorkCard(deletedWorkCard);
		deletedWorkCard = new WorkCard();
		createWorkCardList();
	}
	
	public void saveWorkCard(){
		editWorkCard.setAircraft(selectedAircraft);
		workCardController.saveWorkCard(editWorkCard, sparePartList);
		createWorkCardList();
		clearData();
	}
	
	public void closeWorkCard(){	
		Binding bind;
		
		if(closedWorkCard.getMaintenanceType().equals(MaintenanceType.PREVENTIVE)){
			for(WorkCardInspection binding : closedWorkCard.getWorkCardInspections()){				
				
				bind = binding.getBinding();
				
				if(binding.getBinding().getInspection().getLimitedEngineCycle() != 0){				
					bind.setPreviousEngineCycle(bind.getCurrentEngineCycle());
					bind.setNextEngineCycle(bind.getCurrentEngineCycle() + bind.getInspection().getLimitedEngineCycle());									
				}
				if(binding.getBinding().getInspection().getLimitedFlightHour() != 0){
					bind.setPreviousFlightHour(bind.getCurrentFlightHour());
					bind.setNextFlightHour(bind.getCurrentFlightHour() + bind.getInspection().getLimitedFlightHour());
				}
				if(binding.getBinding().getInspection().getLimitedLandingCycle() != 0){
					bind.setPreviousLandingCycle(bind.getCurrentLandingCycle());
					bind.setNextLandingCyccle(bind.getCurrentLandingCycle() + bind.getInspection().getLimitedLandingCycle());
				}
				if(binding.getBinding().getInspection().getLimitedPropellerCycle() != 0){
					bind.setPreviousPropellerCycle(bind.getCurrentPropellerCycle());
					bind.setNextPropellerCycle(bind.getCurrentPropellerCycle() + bind.getInspection().getLimitedPropellerCycle());
				}
				if(binding.getBinding().getInspection().getLimitedTime() != 0){
					bind.setPreviousDueDate(DateTime.now().toDateMidnight().toDate());
					bind.setNextDueDate(DateTime.now().toDateMidnight().plusMonths(bind.getInspection().getLimitedTime()).toDate());
				
				}				
				
				bindingController.saveBinding(bind);
			}
		}
		closedWorkCard.setWorkCardStatus(WorkCardStatus.COMPLETED);
		workCardController.saveWorkCard(closedWorkCard);
		closedWorkCard = new WorkCard();
	}
	
	public void workCardSelected(){
		sparePartList.clear();
		
		selectedAircraft = editWorkCard.getAircraft();
		aircraftID = selectedAircraft.getId();
		selectedMaintenanceType = editWorkCard.getMaintenanceType().getID();
		
		sparePartList.addAll(editWorkCard.getWorkCardParts());
		
		workcardFieldsetLegend = "Edit Work Card "+"["+editWorkCard.getNumber()+"]";
	}
	
	public void sparePartSelected(){
		editPart = partsController.getPart(selectedPart);
	}
	
	private void createPartSelectItemList(){
		partSelectItemList.clear();

		ArrayList<SpareParts> parts = partsController.getAllParts();
		partSelectItemList.add(new SelectItem(Long.valueOf(0), "Select Part"));
		for(SpareParts part : parts){
			partSelectItemList.add(new SelectItem(part.getId(), part.getName()));
		}
	}
	
	public void addPart(){
		WorkCardParts cardParts = new WorkCardParts();
		cardParts.setSpareParts(editPart);
		sparePartList.add(cardParts);
		
		editPart = new SpareParts();
		selectedPart = Long.valueOf(0);
	}
	
	public void deletePart(WorkCardParts part){
		WorkCardParts deleted = null;
		for(WorkCardParts parts : sparePartList){
			if(part.getSpareParts().getId().equals(parts.getSpareParts().getId()))
				deleted = parts;
		}
		sparePartList.remove(deleted);
	}
	
	private void createAircraftSelectItemList(){
		if(aircraftSelectItemList != null) aircraftSelectItemList.clear();
		if(aircraftFilterOptions != null) aircraftFilterOptions.clear();
				
		aircraftSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		aircraftFilterOptions.add(new SelectItem("", "All"));
		
		ArrayList<Aircraft> aircraftList = aircraftController.getAllAircraft();
		for(Aircraft aircraft : aircraftList){
			aircraftSelectItemList.add(new SelectItem(aircraft.getId(), aircraft.getRegister()));
			aircraftFilterOptions.add(new SelectItem(aircraft.getRegister(), aircraft.getRegister()));
		}
	}
	
	private void createMaintenanceType(){
		if(maintenanceTypeSelectItemList != null) maintenanceTypeSelectItemList.clear();
		
		for(MaintenanceType type : MaintenanceType.values()){
			maintenanceTypeSelectItemList.add(new SelectItem(type.getID(), type.getValue()));
		}
	}
	
	public void maintenanceTypeSelected(){
		editWorkCard.setMaintenanceType(MaintenanceType.find(selectedMaintenanceType));
	}
	
	public void createAllList(){
		createWorkCardList();
		createAircraftSelectItemList();
		createPartSelectItemList();
	}
	
	private void createWorkCardList(){
		if(workCardList != null) workCardList.clear();
		
		workCardList = workCardController.getWorkCardList();
	}
	
	public void aircraftSelected(){
		selectedAircraft = aircraftController.getAircraft(aircraftID);
		if(selectedAircraft != null)
			editWorkCard.setFlightHour(selectedAircraft.getCurrentFlightHour());
	}
	
	public List<WorkCard> getWorkCardList() {
		return workCardList;
	}
	public void setWorkCardList(List<WorkCard> workCardList) {
		this.workCardList = workCardList;
	}
	public WorkCard getEditWorkCard() {
		return editWorkCard;
	}
	public void setEditWorkCard(WorkCard editWorkCard) {
		this.editWorkCard = editWorkCard;
	}
	public List<WorkCard> getFiltered() {
		return filtered;
	}
	public void setFiltered(List<WorkCard> filtered) {
		this.filtered = filtered;
	}

	public ArrayList<SelectItem> getAircraftSelectItemList() {
		return aircraftSelectItemList;
	}

	public void setAircraftSelectItemList(ArrayList<SelectItem> aircraftSelectItemList) {
		this.aircraftSelectItemList = aircraftSelectItemList;
	}

	public AircraftController getAircraftController() {
		return aircraftController;
	}

	public void setAircraftController(AircraftController aircraftController) {
		this.aircraftController = aircraftController;
	}

	public Long getAircraftID() {
		return aircraftID;
	}

	public void setAircraftID(Long aircraftID) {
		this.aircraftID = aircraftID;
	}

	public Aircraft getSelectedAircraft() {
		return selectedAircraft;
	}

	public void setSelectedAircraft(Aircraft selectedAircraft) {
		this.selectedAircraft = selectedAircraft;
	}

	public ArrayList<SelectItem> getMaintenanceTypeSelectItemList() {
		return maintenanceTypeSelectItemList;
	}

	public void setMaintenanceTypeSelectItemList(
			ArrayList<SelectItem> maintenanceTypeSelectItemList) {
		this.maintenanceTypeSelectItemList = maintenanceTypeSelectItemList;
	}

	public String getSelectedMaintenanceType() {
		return selectedMaintenanceType;
	}

	public void setSelectedMaintenanceType(String selectedMaintenanceType) {
		this.selectedMaintenanceType = selectedMaintenanceType;
	}

	public List<WorkCardParts> getSparePartList() {
		return sparePartList;
	}

	public void setSparePartList(List<WorkCardParts> sparePartList) {
		this.sparePartList = sparePartList;
	}

	public List<SelectItem> getPartSelectItemList() {
		return partSelectItemList;
	}

	public void setPartSelectItemList(List<SelectItem> partSelectItemList) {
		this.partSelectItemList = partSelectItemList;
	}

	public Long getSelectedPart() {
		return selectedPart;
	}

	public void setSelectedPart(Long selectedPart) {
		this.selectedPart = selectedPart;
	}

	public SpareParts getEditPart() {
		return editPart;
	}

	public void setEditPart(SpareParts editPart) {
		this.editPart = editPart;
	}

	public String getWorkcardFieldsetLegend() {
		return workcardFieldsetLegend;
	}

	public void setWorkcardFieldsetLegend(String workcardFieldsetLegend) {
		this.workcardFieldsetLegend = workcardFieldsetLegend;
	}

	public WorkCard getDeletedWorkCard() {
		return deletedWorkCard;
	}

	public void setDeletedWorkCard(WorkCard deletedWorkCard) {
		this.deletedWorkCard = deletedWorkCard;
	}

	public WorkCard getClosedWorkCard() {
		return closedWorkCard;
	}

	public void setClosedWorkCard(WorkCard closedWorkCard) {
		this.closedWorkCard = closedWorkCard;
	}

	public List<SelectItem> getStatusFilterOptions() {
		return statusFilterOptions;
	}

	public void setStatusFilterOptions(List<SelectItem> statusFilterOptions) {
		this.statusFilterOptions = statusFilterOptions;
	}

	public List<SelectItem> getTypeFilterOptions() {
		return typeFilterOptions;
	}

	public void setTypeFilterOptions(List<SelectItem> typeFilterOptions) {
		this.typeFilterOptions = typeFilterOptions;
	}

	public List<SelectItem> getAircraftFilterOptions() {
		return aircraftFilterOptions;
	}

	public void setAircraftFilterOptions(List<SelectItem> aircraftFilterOptions) {
		this.aircraftFilterOptions = aircraftFilterOptions;
	}

}
