package am.manager;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import am.controller.EngineController;
import am.model.Aircraft;
import am.model.Engine;

@ManagedBean(name="viewEngineManager")
@SessionScoped
public class ViewEngineManager {
	private ArrayList<SelectItem> engineSelectItemList = new ArrayList<SelectItem>();
	private Long engineID;
	private Engine selectedEngine;
	private EngineController engineController = new EngineController();
	private Aircraft attachedAircraft;
	
	public Aircraft getAttachedAircraft() {
		return attachedAircraft;
	}

	public void setAttachedAircraft(Aircraft attachedAircraft) {
		this.attachedAircraft = attachedAircraft;
	}

	public ArrayList<SelectItem> getEngineSelectItemList() {
		return engineSelectItemList;
	}

	public void setEngineSelectItemList(ArrayList<SelectItem> engineSelectItemList) {
		this.engineSelectItemList = engineSelectItemList;
	}

	public Long getEngineID() {
		return engineID;
	}

	public void setEngineID(Long engineID) {
		this.engineID = engineID;
	}

	public Engine getSelectedEngine() {
		return selectedEngine;
	}

	public void setSelectedEngine(Engine selectedEngine) {
		this.selectedEngine = selectedEngine;
	}

	public ViewEngineManager() {
		createEngineSelectItemList();
	}
	
	private void createEngineSelectItemList(){
		if(engineSelectItemList != null) engineSelectItemList.clear();
		
		ArrayList<Engine> engineList = engineController.getAllEngine();
		engineSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Engine engine : engineList){
			engineSelectItemList.add(new SelectItem(engine.getId(), engine.getUniqeName()));
		}	
	}
	
	public void engineSelected(){
		selectedEngine = engineController.getEngine(engineID);
		attachedAircraft = new Aircraft();
		
		if(selectedEngine != null){
			if(!selectedEngine.getAircraftEngineOne().isEmpty() && selectedEngine.getAircraftEngineOne().iterator().next().getId() != null){
				attachedAircraft = selectedEngine.getAircraftEngineOne().iterator().next();			
			}
			if(!selectedEngine.getAircraftEngineTwo().isEmpty() && selectedEngine.getAircraftEngineTwo().iterator().next().getId() != null){
				attachedAircraft = selectedEngine.getAircraftEngineTwo().iterator().next();
			}	
		}		
	}
	
	public void createAllList(){
		createEngineSelectItemList();
	}
}
