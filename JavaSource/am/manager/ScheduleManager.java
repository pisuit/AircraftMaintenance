package am.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.jboss.aop.Bind;
import org.joda.time.DateTime;
import org.joda.time.Days;

import am.controller.AircraftController;
import am.controller.BindingController;
import am.controller.ChapterController;
import am.controller.PartsController;
import am.controller.ScheduleController;
import am.controller.WorkCardController;
import am.customtype.DeviceType;
import am.customtype.InspectionType;
import am.customtype.MaintenanceType;
import am.datamodel.ScheduleDataModel;
import am.model.Aircraft;
import am.model.Binding;
import am.model.Chapter;
import am.model.PartsCompability;
import am.model.SpareParts;
import am.model.Threshold;
import am.model.WorkCard;
import am.model.WorkCardInspection;
import am.model.WorkCardParts;

@ManagedBean(name="scheduleManager")
@SessionScoped
public class ScheduleManager {
	
	public static String newline = System.getProperty("line.separator");
	private ArrayList<SelectItem> aircraftSelectItemList = new ArrayList<SelectItem>();
	private AircraftController aircraftController = new AircraftController();
	private Long aircraftID;
	private Aircraft selectedAircraft;
	private BindingController bindingController = new BindingController();
	private ArrayList<ScheduleDataModel> scheduleDataModelList = new ArrayList<ScheduleDataModel>();
	private ArrayList<SelectItem> filterOptions = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> typeFilterOptions = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> deviceNameFilterOptions = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> inspectionTypeFilterOptions = new ArrayList<SelectItem>();
	private Threshold editThreshold = new Threshold();
	private ScheduleController scheduleController = new ScheduleController();
	private WorkCard editWorkcard = new WorkCard();
	private ScheduleDataModel selectedSchedule;
	private ArrayList<SpareParts> partList = new ArrayList<SpareParts>();
	private PartsController partsController = new PartsController();
	private WorkCardController workCardController = new WorkCardController();
	private ArrayList<ScheduleDataModel> filtered;
	private ChapterController chapterController = new ChapterController();
	private List<ScheduleDataModel> selectedBinding;
	
	
	public ScheduleManager(){
		createAircraftSelectItemList();
		getThreshold();
		createInspectionTypeFilterOptionsList();
		createChapterFilterOptions();
		createDeviceTypeFilterOptions();
	}
	
	public void createAllList(){
		createChapterFilterOptions();
		createAircraftSelectItemList();
	}
	
	private void createAircraftSelectItemList(){
		if(aircraftSelectItemList != null) aircraftSelectItemList.clear();
		
		aircraftSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		ArrayList<Aircraft> aircraftList = aircraftController.getAllAircraft();
		for(Aircraft aircraft : aircraftList){
			aircraftSelectItemList.add(new SelectItem(aircraft.getId(), aircraft.getRegister()));
		}
	}
	
	private void createInspectionTypeFilterOptionsList(){
		if(inspectionTypeFilterOptions != null) inspectionTypeFilterOptions.clear();
		
		inspectionTypeFilterOptions.add(new SelectItem("","All"));
		for(InspectionType type : InspectionType.values()){
			inspectionTypeFilterOptions.add(new SelectItem(type.getValue(),type.getValue()));
		}
	}
	
	private void createDeviceTypeFilterOptions(){
		if(typeFilterOptions != null) typeFilterOptions.clear();
		
		typeFilterOptions.add(new SelectItem("","All"));
		for(DeviceType type : DeviceType.values()){
			typeFilterOptions.add(new SelectItem(type.getValue(), type.getValue()));
		}
	}
	
	public void aircraftSelected(){
		selectedAircraft = aircraftController.getAircraft(aircraftID);
		createScheduleList();
	}
	
	private void createScheduleList(){
		if(scheduleDataModelList != null) scheduleDataModelList.clear();
		if(!aircraftID.equals(Long.valueOf(0))){
			ScheduleDataModel dataModel;
			DateTime previousDate;
			DateTime nextDate;
			ArrayList<Binding> allBindingList = new ArrayList<Binding>();
			
			ArrayList<Binding> aircraftBindingList = bindingController.getAircraftBinding(selectedAircraft);
			ArrayList<Binding> engineOneBindingList = bindingController.getEngineBinding(selectedAircraft.getEngineOne());
			ArrayList<Binding> engineTwoBindingList = bindingController.getEngineBinding(selectedAircraft.getEngineTwo());
			ArrayList<Binding> propellerOneBindingList = bindingController.getPropellerBinding(selectedAircraft.getPropellerOne());
			ArrayList<Binding> propellerTwoBindingList = bindingController.getPropellerBinding(selectedAircraft.getPropellerTwo());
		
			allBindingList.addAll(aircraftBindingList);
			allBindingList.addAll(engineOneBindingList);
			allBindingList.addAll(engineTwoBindingList);
			allBindingList.addAll(propellerOneBindingList);
			allBindingList.addAll(propellerTwoBindingList);
			
			for(Binding binding : allBindingList){
				dataModel = new ScheduleDataModel();
				previousDate = new DateTime(binding.getPreviousDueDate());
				nextDate = new DateTime(binding.getNextDueDate());
				
				dataModel.setChapter(binding.getChapter());
				dataModel.setId(binding.getId());
				dataModel.setInspection(binding.getInspection());
				dataModel.setDeviceType(binding.getDeviceType());
				dataModel.setBinding(binding);
				if(binding.getDeviceType().equals(DeviceType.AIRCRAFT)){
					dataModel.setAircraft(binding.getAircraft());
					dataModel.setDeviceName(binding.getAircraft().getRegister());
				}
				if(binding.getDeviceType().equals(DeviceType.ENGINE)){
					dataModel.setEngine(binding.getEngine());
					dataModel.setDeviceName(binding.getEngine().getUniqeName());
				}
				if(binding.getDeviceType().equals(DeviceType.PROPELLER)){
					dataModel.setPropeller(binding.getPropeller());
					dataModel.setDeviceName(binding.getPropeller().getUniqeName());
				}
				if(binding.getInspection().getLimitedTime() != 0){
					dataModel.setDayLeft(Days.daysBetween(new DateTime().now().toDateMidnight(), nextDate.toDateMidnight()).getDays());
				} else {
					dataModel.setDayLeft(999999999);
				}
				if(binding.getInspection().getLimitedEngineCycle() != 0){
					dataModel.setEngineCycleLeft(binding.getNextEngineCycle() - binding.getCurrentEngineCycle());
				} else {
					dataModel.setEngineCycleLeft(999999999);
				}
				if(binding.getInspection().getLimitedFlightHour() != 0){
					dataModel.setHourLeft(binding.getNextFlightHour() - binding.getCurrentFlightHour());
				} else {
					dataModel.setHourLeft(999999999);
				}
				if(binding.getInspection().getLimitedLandingCycle() != 0){
					dataModel.setLandingCycleLeft(binding.getNextLandingCyccle() - binding.getCurrentLandingCycle());
				} else {
					dataModel.setLandingCycleLeft(999999999);
				}
				if(binding.getInspection().getLimitedPropellerCycle() != 0){
					dataModel.setPropellerCycleLeft(binding.getNextPropellerCycle() - binding.getCurrentPropellerCycle());
				} else {
					dataModel.setPropellerCycleLeft(999999999);
				}			
				scheduleDataModelList.add(dataModel);
			}
			createFilterOptions();
		}
	}
	
	private void createChapterFilterOptions(){
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
	
	private void createFilterOptions(){
//		if(filterOptions != null) filterOptions.clear();
//		if(typeFilterOptions != null) typeFilterOptions.clear();
		if(deviceNameFilterOptions != null) deviceNameFilterOptions.clear();
//		String tempChapter = "";
		DeviceType tempType = DeviceType.NONE;
		String tempDeviceName = "";
		
		if(scheduleDataModelList.size() != 0) {
//			filterOptions.add(new SelectItem("", "All"));
//			typeFilterOptions.add(new SelectItem("", "All"));
			deviceNameFilterOptions.add(new SelectItem("", "All"));
		}
		for(ScheduleDataModel model : scheduleDataModelList){
//			if(!tempChapter.equals(model.getChapter().toString())){
//				filterOptions.add(new SelectItem(model.getChapter().toString(), model.getChapter().toString()));
//			}
//			if(!tempType.equals(model.getDeviceType())){
//				typeFilterOptions.add(new SelectItem(model.getDeviceType().getValue(), model.getDeviceType().getValue()));
//			}
			if(model.getAircraft() != null){
				if(!tempDeviceName.equals(model.getAircraft().getRegister())){
					deviceNameFilterOptions.add(new SelectItem(model.getAircraft().getRegister(), model.getAircraft().getRegister()));
				}
				tempDeviceName = model.getAircraft().getRegister();
			}
			if(model.getEngine() != null){
				if(!tempDeviceName.equals(model.getEngine().getUniqeName())){
					deviceNameFilterOptions.add(new SelectItem(model.getEngine().getUniqeName(), model.getEngine().getUniqeName()));
				}
				tempDeviceName = model.getEngine().getUniqeName();
			}
			if(model.getPropeller() != null){
				if(!tempDeviceName.equals(model.getPropeller().getUniqeName())){
					deviceNameFilterOptions.add(new SelectItem(model.getPropeller().getUniqeName(), model.getPropeller().getUniqeName()));
				}
				tempDeviceName = model.getPropeller().getUniqeName();
			}
			
//			tempChapter = model.getChapter().toString();
			tempType = model.getDeviceType();
		}
	}
	
	private void getThreshold(){
		Threshold threshold = scheduleController.getThreshold();
		if(threshold != null) editThreshold = threshold;
	}
	
	public void saveThreshold(){
		scheduleController.saveThreshold(editThreshold);
	}
	
	public void createJob(){
		editWorkcard = new WorkCard();
		Set<WorkCardInspection> inspections = new HashSet<WorkCardInspection>();
		Set<WorkCardParts> parts = new HashSet<WorkCardParts>();
		WorkCardInspection workCardInspection;
		WorkCardParts workCardParts;
		
		editWorkcard.setCreateDate(Calendar.getInstance().getTime());
		editWorkcard.setFlightHour(selectedAircraft.getCurrentFlightHour());
		editWorkcard.setAircraft(selectedAircraft);
		editWorkcard.setMaintenanceType(MaintenanceType.PREVENTIVE);
		List<String> inspNameList = new ArrayList<String>();
		for(ScheduleDataModel data : selectedBinding){
			workCardInspection = new WorkCardInspection();
			workCardInspection.setBinding(data.getBinding());
			inspections.add(workCardInspection);
			
			inspNameList.add(data.getBinding().getInspection().getName());
			
			for(SpareParts part : data.getInspection().getPartList()){
				workCardParts = new WorkCardParts();
				workCardParts.setSpareParts(part);
				parts.add(workCardParts);
			}
		}
		editWorkcard.setRequireRemark(StringUtils.join(inspNameList, newline));
		editWorkcard.setWorkCardParts(parts);
		editWorkcard.setWorkCardInspections(inspections);
		
//		ArrayList<SpareParts> parts = new ArrayList<SpareParts>();
//		if(partList != null) partList.clear();
//		
//		if(selectedSchedule.getDeviceType().equals(DeviceType.AIRCRAFT)){
//			editWorkcard.setAircraft(selectedSchedule.getAircraft());
//		}
//		if(selectedSchedule.getDeviceType().equals(DeviceType.ENGINE)){
//			editWorkcard.setEngine(selectedSchedule.getEngine());
//		}
//		if(selectedSchedule.getDeviceType().equals(DeviceType.PROPELLER)){
//			editWorkcard.setPropeller(selectedSchedule.getPropeller());
//		}
//		editWorkcard.setCreateDate(Calendar.getInstance().getTime());
//		editWorkcard.setFlightHour(selectedSchedule.getBinding().getCurrentFlightHour());
//		editWorkcard.setEngineCycle(selectedSchedule.getBinding().getCurrentEngineCycle());
//		editWorkcard.setPropellerCycle(selectedSchedule.getBinding().getCurrentPropellerCycle());
//		editWorkcard.setLandingCycle(selectedSchedule.getBinding().getCurrentLandingCycle());
//		editWorkcard.setBinding(selectedSchedule.getBinding());
//		
//		parts.addAll(selectedSchedule.getInspection().getPartList());
//		
//		for(SpareParts spareParts : parts){
//			partList.add(partsController.getPart(spareParts.getId()));
//		}
	}
	
	public void createWorkCard(){
		workCardController.saveWorkCard(editWorkcard);
	}

	public ArrayList<ScheduleDataModel> getFiltered() {
		return filtered;
	}

	public void setFiltered(ArrayList<ScheduleDataModel> filtered) {
		this.filtered = filtered;
	}
	
	public ArrayList<SelectItem> getAircraftSelectItemList() {
		return aircraftSelectItemList;
	}

	public void setAircraftSelectItemList(
			ArrayList<SelectItem> aircraftSelectItemList) {
		this.aircraftSelectItemList = aircraftSelectItemList;
	}

	public WorkCard getEditWorkcard() {
		return editWorkcard;
	}

	public void setEditWorkcard(WorkCard editWorkcard) {
		this.editWorkcard = editWorkcard;
	}

	public ArrayList<SelectItem> getTypeFilterOptions() {
		return typeFilterOptions;
	}

	public void setTypeFilterOptions(ArrayList<SelectItem> typeFilterOptions) {
		this.typeFilterOptions = typeFilterOptions;
	}

	public ArrayList<SpareParts> getPartList() {
		return partList;
	}

	public void setPartList(ArrayList<SpareParts> partList) {
		this.partList = partList;
	}

	public ScheduleDataModel getSelectedSchedule() {
		return selectedSchedule;
	}

	public void setSelectedSchedule(ScheduleDataModel selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}

	public ArrayList<SelectItem> getDeviceNameFilterOptions() {
		return deviceNameFilterOptions;
	}

	public void setDeviceNameFilterOptions(
			ArrayList<SelectItem> deviceNameFilterOptions) {
		this.deviceNameFilterOptions = deviceNameFilterOptions;
	}

	public ArrayList<SelectItem> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(ArrayList<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}

	public Threshold getEditThreshold() {
		return editThreshold;
	}

	public void setEditThreshold(Threshold editThreshold) {
		this.editThreshold = editThreshold;
	}

	public ArrayList<ScheduleDataModel> getScheduleDataModelList() {
		return scheduleDataModelList;
	}

	public void setScheduleDataModelList(
			ArrayList<ScheduleDataModel> scheduleDataModelList) {
		this.scheduleDataModelList = scheduleDataModelList;
	}

	public Long getAircraftID() {
		return aircraftID;
	}

	public void setAircraftID(Long aircraftID) {
		this.aircraftID = aircraftID;
	}

	public ArrayList<SelectItem> getInspectionTypeFilterOptions() {
		return inspectionTypeFilterOptions;
	}

	public void setInspectionTypeFilterOptions(
			ArrayList<SelectItem> inspectionTypeFilterOptions) {
		this.inspectionTypeFilterOptions = inspectionTypeFilterOptions;
	}

	public List<ScheduleDataModel> getSelectedBinding() {
		return selectedBinding;
	}

	public void setSelectedBinding(List<ScheduleDataModel> selectedBinding) {
		this.selectedBinding = selectedBinding;
	}

	public Aircraft getSelectedAircraft() {
		return selectedAircraft;
	}

	public void setSelectedAircraft(Aircraft selectedAircraft) {
		this.selectedAircraft = selectedAircraft;
	}
}
