package am.manager;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import am.controller.AircraftController;
import am.model.Aircraft;

@ManagedBean(name="viewAircraftManager")
@SessionScoped
public class ViewAircraftManager {
	private Long aircraftID;
	private ArrayList<SelectItem> aircraftSelectItemList = new ArrayList<SelectItem>();
	private Aircraft selectedAircraft;
	private AircraftController aircraftController = new AircraftController();
	
	public ArrayList<SelectItem> getAircraftSelectItemList() {
		return aircraftSelectItemList;
	}

	public void setAircraftSelectItemList(
			ArrayList<SelectItem> aircraftSelectItemList) {
		this.aircraftSelectItemList = aircraftSelectItemList;
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

	public ViewAircraftManager(){
		createAircraftSelectItemList();
	}
	
	private void createAircraftSelectItemList(){
		if(aircraftSelectItemList != null) aircraftSelectItemList.clear();
		
		ArrayList<Aircraft> aircraftList = aircraftController.getAllAircraft();
		aircraftSelectItemList.add(new SelectItem(Long.valueOf(0), "Select One"));
		for(Aircraft aircraft : aircraftList){
			aircraftSelectItemList.add(new SelectItem(aircraft.getId(), aircraft.getRegister()));
		}
	}
	
	public void aircraftSelected(){
		selectedAircraft = aircraftController.getAircraft(aircraftID);
	}
	
	public void createAllList(){
		createAircraftSelectItemList();
	}
}
