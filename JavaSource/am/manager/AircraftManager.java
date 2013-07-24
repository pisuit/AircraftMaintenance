package am.manager;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import am.controller.AircraftController;
import am.controller.EngineController;
import am.controller.PropellerController;
import am.customtype.DataStatus;
import am.customtype.Status;
import am.model.Aircraft;
import am.model.Engine;
import am.model.Propeller;

@SessionScoped
@ManagedBean(name="aircraftManager")
public class AircraftManager {
	private ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();
	private Aircraft editAircraft = new Aircraft();
	private Aircraft deletedAircraft = new Aircraft();
	private AircraftController aircraftController = new AircraftController();
	private EngineController engineController = new EngineController();
	private PropellerController propellerController = new PropellerController();
	private String inputFieldsetLegend = "Create Aircraft";
	private ArrayList<SelectItem> engineSelectItemList = new ArrayList<SelectItem>();
	private ArrayList<SelectItem> propellerSelectItemList = new ArrayList<SelectItem>();
	private Long engineOneID = Long.valueOf(0);
	private Long engineTwoID = Long.valueOf(0);
	private Long propellerOneID = Long.valueOf(0);
	private Long propellerTwoID = Long.valueOf(0);
	private Engine oldEngineOne = new Engine();
	private Engine oldEngineTwo = new Engine();
	private Propeller oldPropellerOne = new Propeller();
	private Propeller oldPropellerTwo = new Propeller();
	
	public Engine getOldEngineOne() {
		return oldEngineOne;
	}

	public void setOldEngineOne(Engine oldEngineOne) {
		this.oldEngineOne = oldEngineOne;
	}

	public Engine getOldEngineTwo() {
		return oldEngineTwo;
	}

	public void setOldEngineTwo(Engine oldEngineTwo) {
		this.oldEngineTwo = oldEngineTwo;
	}

	public Propeller getOldPropellerOne() {
		return oldPropellerOne;
	}

	public void setOldPropellerOne(Propeller oldPropellerOne) {
		this.oldPropellerOne = oldPropellerOne;
	}

	public Propeller getOldPropellerTwo() {
		return oldPropellerTwo;
	}

	public void setOldPropellerTwo(Propeller oldPropellerTwo) {
		this.oldPropellerTwo = oldPropellerTwo;
	}

	public Long getEngineOneID() {
		return engineOneID;
	}

	public void setEngineOneID(Long engineOneID) {
		this.engineOneID = engineOneID;
	}

	public Long getEngineTwoID() {
		return engineTwoID;
	}

	public void setEngineTwoID(Long engineTwoID) {
		this.engineTwoID = engineTwoID;
	}

	public Long getPropellerOneID() {
		return propellerOneID;
	}

	public void setPropellerOneID(Long propellerOneID) {
		this.propellerOneID = propellerOneID;
	}

	public Long getPropellerTwoID() {
		return propellerTwoID;
	}

	public void setPropellerTwoID(Long propellerTwoID) {
		this.propellerTwoID = propellerTwoID;
	}

	public ArrayList<SelectItem> getEngineSelectItemList() {
		return engineSelectItemList;
	}

	public void setEngineSelectItemList(ArrayList<SelectItem> engineSelectItemList) {
		this.engineSelectItemList = engineSelectItemList;
	}

	public ArrayList<SelectItem> getPropellerSelectItemList() {
		return propellerSelectItemList;
	}

	public void setPropellerSelectItemList(
			ArrayList<SelectItem> propellerSelectItemList) {
		this.propellerSelectItemList = propellerSelectItemList;
	}

	public void setAircraftList(ArrayList<Aircraft> aircraftList) {
		this.aircraftList = aircraftList;
	}

	public Aircraft getDeletedAircraft() {
		return deletedAircraft;
	}

	public void setDeletedAircraft(Aircraft deletedAircraft) {
		this.deletedAircraft = deletedAircraft;
	}
	
	public String getInputFieldsetLegend() {
		return inputFieldsetLegend;
	}

	public void setInputFieldsetLegend(String inputFieldsetLegend) {
		this.inputFieldsetLegend = inputFieldsetLegend;
	}

	public Aircraft getEditAircraft() {
		return editAircraft;
	}

	public void setEditAircraft(Aircraft editAircraft) {
		this.editAircraft = editAircraft;
	}

	public ArrayList<Aircraft> getAircraftList() {
		return aircraftList;
	}

	public AircraftManager() {
		createAircraftList();
		createEngineSelectItemList();
		createPropellerSelectItemList();
	}
	
	private void createAircraftList(){
		if(aircraftList != null) aircraftList.clear();
		aircraftList = aircraftController.getAllAircraft();
	}
	
	public void saveAircraft(){
		aircraftController.saveAircraft(editAircraft, oldEngineOne, oldEngineTwo, oldPropellerOne, oldPropellerTwo);
		createAircraftList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Saved"));
	}
	
	public void deleteAircraft(){
		aircraftController.deleteAircraft(deletedAircraft);
		createAircraftList();
		clearData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful !!", "Deleted"));
	}
	
	public void setSelectedAircraft(){
		createEngineSelectItemList();
		createPropellerSelectItemList();
		
		if(editAircraft.getEngineOne() != null) {
			engineOneID = editAircraft.getEngineOne().getId();
			oldEngineOne = editAircraft.getEngineOne();
		} else {
			engineOneID = Long.valueOf(0);
		}
		
		if(editAircraft.getEngineTwo() != null) {
			engineTwoID = editAircraft.getEngineTwo().getId();
			oldEngineTwo = editAircraft.getEngineTwo();
		} else {
			engineTwoID = Long.valueOf(0);
		}
					
		if(editAircraft.getPropellerOne() != null) {
			propellerOneID = editAircraft.getPropellerOne().getId();
			oldPropellerOne = editAircraft.getPropellerOne();
		} else {
			propellerOneID = Long.valueOf(0);
		}
					
		if(editAircraft.getPropellerTwo() != null) {
			propellerTwoID = editAircraft.getPropellerTwo().getId();
			oldPropellerTwo = editAircraft.getPropellerTwo();
		} else {
			propellerTwoID = Long.valueOf(0);
		}					
		inputFieldsetLegend = "Edit Aircraft " + "[" +editAircraft.getRegister()+ "]";
		
	}
	
	public void clearData(){
		editAircraft = new Aircraft();
		deletedAircraft = new Aircraft();
		inputFieldsetLegend = "Create Aircraft";
		engineOneID = Long.valueOf(0);
		engineTwoID = Long.valueOf(0);
		propellerOneID = Long.valueOf(0);
		propellerTwoID = Long.valueOf(0);
		oldEngineOne = new Engine();
		oldEngineTwo = new Engine();
		oldPropellerOne = new Propeller();
		oldPropellerTwo = new Propeller();
		createEngineSelectItemList();
		createPropellerSelectItemList();
		
	}
	
	private void createEngineSelectItemList(){
		if(engineSelectItemList != null) engineSelectItemList.clear();
		ArrayList<Engine> engineList = engineController.getAllAvailableEngine();
		
		engineSelectItemList.add(new SelectItem(Long.valueOf(0), "None"));
		if(editAircraft.getId() != null){
			if(editAircraft.getEngineOne() != null){
				engineSelectItemList.add(new SelectItem(editAircraft.getEngineOne().getId(), editAircraft.getEngineOne().getUniqeName()));
			}
			if(editAircraft.getEngineTwo() != null){
				engineSelectItemList.add(new SelectItem(editAircraft.getEngineTwo().getId(), editAircraft.getEngineTwo().getUniqeName()));
			}
		}
		
		for(Engine engine : engineList){
			engineSelectItemList.add(new SelectItem(engine.getId(), engine.getUniqeName()));						
		}
	}
	
	private void createPropellerSelectItemList(){
		if(propellerSelectItemList != null) propellerSelectItemList.clear();
		ArrayList<Propeller> propellerList = propellerController.getAllAvailablePropeller();
		
		propellerSelectItemList.add(new SelectItem(Long.valueOf(0), "None"));
		if(editAircraft.getId() != null){
			if(editAircraft.getPropellerOne() != null){
				propellerSelectItemList.add(new SelectItem(editAircraft.getPropellerOne().getId(), editAircraft.getPropellerOne().getUniqeName()));
			}
			if(editAircraft.getPropellerTwo() != null){
				propellerSelectItemList.add(new SelectItem(editAircraft.getPropellerTwo().getId(), editAircraft.getPropellerTwo().getUniqeName()));
			}
		}
		
		for(Propeller propeller : propellerList){
			propellerSelectItemList.add(new SelectItem(propeller.getId(), propeller.getUniqeName()));
		}
	}
	
	public void engineSelected(String str){
		if(str.equals("engineOne")){
			Engine engine = engineController.getEngine(engineOneID);
			editAircraft.setEngineOne(engine);
		} else {
			Engine engine = engineController.getEngine(engineTwoID);
			editAircraft.setEngineTwo(engine);
		}
	}
	
	public void propellerSelected(String str){
		if(str.equals("propellerOne")){
			Propeller propeller = propellerController.getPropeller(propellerOneID);
			editAircraft.setPropellerOne(propeller);
		} else {
			Propeller propeller = propellerController.getPropeller(propellerTwoID);
			editAircraft.setPropellerTwo(propeller);
		}
	}
	
	public void createAllList(){
		createAircraftList();
		createEngineSelectItemList();
		createPropellerSelectItemList();
	}
}
