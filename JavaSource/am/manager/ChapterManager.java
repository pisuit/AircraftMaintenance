package am.manager;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import am.controller.ChapterController;
import am.customtype.DataStatus;
import am.model.Chapter;

@ManagedBean(name="chapterManager")
@SessionScoped
public class ChapterManager {
	
	private Chapter editChapter = new Chapter();
	private Chapter deletedChapter = new Chapter();
	private ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
	private ChapterController chapterController = new ChapterController();
	private String inputFieldsetLegend = "Create Chapter";
	private ArrayList<Chapter> filtered;
	

	public ChapterManager() {
		createChapterList();
	}
	
	public void saveChapter(){
		chapterController.saveChapter(editChapter);
		createChapterList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deleteChapter(){
		deletedChapter.setDataStatus(DataStatus.DELETED);
		chapterController.saveChapter(deletedChapter);
		createChapterList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void createChapterList(){
		if(chapterList != null) chapterList.clear();
		chapterList = chapterController.getAllChapter();
	}
	
	public void setSelectedChapter(){
		inputFieldsetLegend = "Edit Chapter "+ "[" +editChapter.getChapterName()+ "]" ;
	}
	
	public void createAllList(){
		createChapterList();
	}
	
	public void clearData(){
		editChapter = new Chapter();
		deletedChapter = new Chapter();
		inputFieldsetLegend = "Create Chapter";
	}

	public ArrayList<Chapter> getFiltered() {
		return filtered;
	}

	public void setFiltered(ArrayList<Chapter> filtered) {
		this.filtered = filtered;
	}
	
	public Chapter getDeletedChapter() {
		return deletedChapter;
	}

	public void setDeletedChapter(Chapter deletedChapter) {
		this.deletedChapter = deletedChapter;
	}

	public Chapter getEditChapter() {
		return editChapter;
	}

	public void setEditChapter(Chapter editChapter) {
		this.editChapter = editChapter;
	}

	public ArrayList<Chapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(ArrayList<Chapter> chapterList) {
		this.chapterList = chapterList;
	}

	public String getInputFieldsetLegend() {
		return inputFieldsetLegend;
	}

	public void setInputFieldsetLegend(String inputFieldsetLegend) {
		this.inputFieldsetLegend = inputFieldsetLegend;
	}
}
