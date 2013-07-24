package am.manager;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import am.controller.PropellerController;
import am.model.Aircraft;
import am.model.Engine;
import am.model.Propeller;

@ManagedBean(name="viewPropellerManager")
@SessionScoped
public class ViewPropellerManager {
	private ArrayList<SelectItem> propellerSelectItemList = new ArrayList<SelectItem>();
	private Long propellerID;
	private PropellerController propellerController = new PropellerController();
	private Propeller selectedPropeller;
	private Aircraft attachedAircraft;
	
	public ArrayList<SelectItem> getPropellerSelectItemList() {
		return propellerSelectItemList;
	}
	public void setPropellerSelectItemList(
			ArrayList<SelectItem> propellerSelectItemList) {
		this.propellerSelectItemList = propellerSelectItemList;
	}
	public Long getPropellerID() {
		return propellerID;
	}
	public void setPropellerID(Long propellerID) {
		this.propellerID = propellerID;
	}
	public Propeller getSelectedPropeller() {
		return selectedPropeller;
	}
	public void setSelectedPropeller(Propeller selectedPropeller) {
		this.selectedPropeller = selectedPropeller;
	}
	public Aircraft getAttachedAircraft() {
		return attachedAircraft;
	}
	public void setAttachedAircraft(Aircraft attachedAircraft) {
		this.attachedAircraft = attachedAircraft;
	}
	
	public ViewPropellerManager(){
		createPropellerSelectItemList();
	}
	
	private void createPropellerSelectItemList(){
		if(propellerSelectItemList != null) propellerSelectItemList.clear();
		
		ArrayList<Propeller> propellerList = propellerController.getAllPropeller();
		propellerSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Propeller propeller : propellerList){
			propellerSelectItemList.add(new SelectItem(propeller.getId(), propeller.getUniqeName()));
		}	
	}
	
	public void propellerSelected(){
		selectedPropeller = propellerController.getPropeller(propellerID);
		attachedAircraft = new Aircraft();
		
		if(selectedPropeller != null){
			if(!selectedPropeller.getAircraftPropellerOne().isEmpty() && selectedPropeller.getAircraftPropellerOne().iterator().next().getId() != null){
				attachedAircraft = selectedPropeller.getAircraftPropellerOne().iterator().next();			
			}
			if(!selectedPropeller.getAircraftPropellerTwo().isEmpty() && selectedPropeller.getAircraftPropellerTwo().iterator().next().getId() != null){
				attachedAircraft = selectedPropeller.getAircraftPropellerTwo().iterator().next();
			}	
		}		
	}
	
	public void createAllList(){
		createPropellerSelectItemList();
	}
}
