package am.manager;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import am.controller.ChapterController;
import am.controller.InspectionController;
import am.customtype.DataStatus;
import am.customtype.InspectionType;
import am.model.Chapter;
import am.model.Inspection;

@ManagedBean(name="inspectionManager")
@SessionScoped
public class InspectionManager {
	
	private InspectionController inspectionController = new InspectionController();
	private ChapterController chapterController = new ChapterController();
	private Inspection editInspection = new Inspection();
	private Inspection deletedInspection = new Inspection();
	private ArrayList<Inspection> inspectionList = new ArrayList<Inspection>();
	private String inputFieldsetLegend = "Create Inspection";
	private Long chapterID = Long.valueOf(0);
	private ArrayList<SelectItem> chapterSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> filterOptions = new ArrayList<SelectItem>();
	private boolean isChapterLocked = false;
	private String dayType = "m";
	private InspectionType selectedInspectionType = InspectionType.MINOR;
	private ArrayList<SelectItem> inspectionTypeSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> typeFilterOptions = new ArrayList<SelectItem>();
	private ArrayList<Inspection> filtered;
	
	

	public InspectionManager(){
		createInspectionList();
		createChapterSelectItemList();
		createFilteroptions();
		createInspectionTypeSelectItemList();
		createTypeFilterOptions();
	}
	
	private void createInspectionList(){
		if(inspectionList != null) inspectionList.clear();
		inspectionList = inspectionController.getAllInspection();
	}
	
	private void createChapterSelectItemList(){
		if(chapterSelectItemList != null) chapterSelectItemList.clear();
		ArrayList<Chapter> chapterList = chapterController.getAllChapter();
		
		chapterSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Chapter chapter : chapterList){
			chapterSelectItemList.add(new SelectItem(chapter.getId(), chapter.getChapterName()));
		}		
	}
	
	public void saveInspection(){
		if(dayType.equals("y")){
			editInspection.setLimitedTime(editInspection.getLimitedTime()*12);
		}
		inspectionController.saveInspection(editInspection);
		createInspectionList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deleteInspection(){
		deletedInspection.setDataStatus(DataStatus.DELETED);
		inspectionController.saveInspection(deletedInspection);
		createInspectionList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void chapterSelected(){
		Chapter chapter = chapterController.getChapter(chapterID);
		editInspection.setChapter(chapter);
	}
	
	public void clearData(){
		editInspection = new Inspection();
		deletedInspection = new Inspection();
		inputFieldsetLegend = "Create Inspection";
		selectedInspectionType = InspectionType.MINOR;
		
		if(isChapterLocked == false){
			chapterID = Long.valueOf(0);
		} else {
			Chapter chapter = chapterController.getChapter(chapterID);
			editInspection.setChapter(chapter);
		}
	}
	
	public void createAllList(){
		createInspectionList();
		createChapterSelectItemList();
		createFilteroptions();
		createTypeFilterOptions();
	}
	
	public void setSelectedInspection(){
		inputFieldsetLegend = "Edit Inspection "+ "[" +editInspection.getName()+ "]";
		selectedInspectionType = editInspection.getType();
		if(editInspection.getChapter() != null && editInspection.getChapter().getId() != null){
			chapterID = editInspection.getChapter().getId();
			isChapterLocked = false;
		} else {
			chapterID = Long.valueOf(0);
			isChapterLocked = false;
		}
	}
	
	private void createFilteroptions(){
		if(filterOptions != null) filterOptions.clear();
		ArrayList<Chapter> chapterList = chapterController.getAllChapter();
		
		filterOptions.add(new SelectItem("", "All"));
		for(Chapter chapter : chapterList){
			filterOptions.add(new SelectItem(chapter.toString(), chapter.toString()));
		}
	}
	
	private void createTypeFilterOptions(){
		if(typeFilterOptions != null) typeFilterOptions.clear();
		
		typeFilterOptions.add(new SelectItem("","All"));
		for(InspectionType type : InspectionType.values()){
			typeFilterOptions.add(new SelectItem(type.getValue(),type.getValue()));
		}
	}
	
	public void inspectionTypeSelected(){
		editInspection.setType(selectedInspectionType);
	}
	
	private void createInspectionTypeSelectItemList(){
		if(inspectionTypeSelectItemList != null) inspectionTypeSelectItemList.clear();
		
		for(InspectionType type : InspectionType.values()){
			inspectionTypeSelectItemList.add(new SelectItem(type.getID(),type.getValue()));
		}
	}

	public ArrayList<Inspection> getFiltered() {
		return filtered;
	}

	public void setFiltered(ArrayList<Inspection> filtered) {
		this.filtered = filtered;
	}
	
	public Inspection getEditInspection() {
		return editInspection;
	}

	public void setEditInspection(Inspection editInspection) {
		this.editInspection = editInspection;
	}

	public Long getChapterID() {
		return chapterID;
	}

	public void setChapterID(Long chapterID) {
		this.chapterID = chapterID;
	}

	public boolean isChapterLocked() {
		return isChapterLocked;
	}

	public void setChapterLocked(boolean isChapterLocked) {
		this.isChapterLocked = isChapterLocked;
	}

	public ArrayList<SelectItem> getTypeFilterOptions() {
		return typeFilterOptions;
	}

	public void setTypeFilterOptions(ArrayList<SelectItem> typeFilterOptions) {
		this.typeFilterOptions = typeFilterOptions;
	}

	public ArrayList<SelectItem> getInspectionTypeSelectItemList() {
		return inspectionTypeSelectItemList;
	}

	public void setInspectionTypeSelectItemList(
			ArrayList<SelectItem> inspectionTypeSelectItemList) {
		this.inspectionTypeSelectItemList = inspectionTypeSelectItemList;
	}

	public InspectionType getSelectedInspectionType() {
		return selectedInspectionType;
	}

	public void setSelectedInspectionType(InspectionType selectedInspectionType) {
		this.selectedInspectionType = selectedInspectionType;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public ArrayList<SelectItem> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(ArrayList<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}

	public Inspection getDeletedInspection() {
		return deletedInspection;
	}

	public void setDeletedInspection(Inspection deletedInspection) {
		this.deletedInspection = deletedInspection;
	}

	public ArrayList<Inspection> getInspectionList() {
		return inspectionList;
	}

	public void setInspectionList(ArrayList<Inspection> inspectionList) {
		this.inspectionList = inspectionList;
	}

	public String getInputFieldsetLegend() {
		return inputFieldsetLegend;
	}

	public void setInputFieldsetLegend(String inputFieldsetLegend) {
		this.inputFieldsetLegend = inputFieldsetLegend;
	}

	public ArrayList<SelectItem> getChapterSelectItemList() {
		return chapterSelectItemList;
	}

	public void setChapterSelectItemList(ArrayList<SelectItem> chapterSelectItemList) {
		this.chapterSelectItemList = chapterSelectItemList;
	}
}
