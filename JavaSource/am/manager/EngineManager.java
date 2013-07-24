package am.manager;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import am.controller.EngineController;
import am.customtype.DataStatus;
import am.model.Engine;

@ManagedBean(name="engineManager")
@SessionScoped
public class EngineManager {
	private String inputFieldLegend = "Create Engine";
	private ArrayList<Engine> engineList = new ArrayList<Engine>();
	private Engine editEngine = new Engine();
	private Engine deletedEngine = new Engine();
	private EngineController engineController = new EngineController();

	public Engine getDeletedEngine() {
		return deletedEngine;
	}

	public void setDeletedEngine(Engine deletedEngine) {
		this.deletedEngine = deletedEngine;
	}

	public ArrayList<Engine> getEngineList() {
		return engineList;
	}

	public void setEngineList(ArrayList<Engine> engineList) {
		this.engineList = engineList;
	}

	public Engine getEditEngine() {
		return editEngine;
	}

	public void setEditEngine(Engine editEngine) {
		this.editEngine = editEngine;
	}

	public String getInputFieldLegend() {
		return inputFieldLegend;
	}

	public void setInputFieldLegend(String inputFieldLegend) {
		this.inputFieldLegend = inputFieldLegend;
	}
	
	public EngineManager(){
		createEngineList();
	}
	
	public void createEngineList(){
		if(engineList != null) engineList.clear();
		engineList = engineController.getAllEngine();
	}
	
	public void saveEngine(){
		engineController.saveEngine(editEngine);
		createEngineList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is saved"));
	}
	
	public void deleteEngine(){
		deletedEngine.setDataStatus(DataStatus.DELETED);
		engineController.saveEngine(deletedEngine);
		createEngineList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Data is deleted"));
	}
	
	public void clearData(){
		editEngine = new Engine();
		deletedEngine = new Engine();
		inputFieldLegend = "Create Engine";
	}
	
	public void setLegend(){
		inputFieldLegend = "Edit Engine "+ "[" +editEngine.getUniqeName()+ "]";
	}
	
	public void createAllList(){
		createEngineList();
	}

}
