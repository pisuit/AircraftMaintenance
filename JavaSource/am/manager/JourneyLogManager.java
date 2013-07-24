package am.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import am.controller.AircraftController;
import am.controller.JourneyLogController;
import am.model.Aircraft;
import am.model.JourneyLog;
import am.model.JourneyTrip;

@ManagedBean(name="journeyLogManager")
@SessionScoped
public class JourneyLogManager {
	private JourneyLogController journeyLogController = new JourneyLogController();
	private List<JourneyLog> journeyLogList = new ArrayList<JourneyLog>();
	private JourneyLog editJourneyLog = new JourneyLog();
	private List<SelectItem> aircraftSelectItemList = new ArrayList<SelectItem>();
	private AircraftController aircraftController = new AircraftController();
	private Aircraft selectedAircraft;
	private Long aircraftID = Long.valueOf(0);
	private List<JourneyTrip> journeyTripList = new ArrayList<JourneyTrip>();
	
	public JourneyLogManager(){
		createJourneyLogList();
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
	
	public void aircraftSelected(){
		selectedAircraft = aircraftController.getAircraft(aircraftID);
		if(selectedAircraft != null){
			editJourneyLog.setAirframeLastTime(selectedAircraft.getCurrentFlightHour());
			editJourneyLog.setLeftEngineLastTime(selectedAircraft.getEngineOne().getCurrentFlightHour());
			editJourneyLog.setRightEngineLastTime(selectedAircraft.getEngineTwo().getCurrentFlightHour());
			editJourneyLog.setLeftPropellerLastTime(selectedAircraft.getPropellerOne().getCurrentFlightHour());
			editJourneyLog.setRightPropellerLastTime(selectedAircraft.getPropellerTwo().getCurrentFlightHour());
			editJourneyLog.setLandingCycleLastTime(selectedAircraft.getCurrentLandingCycle());
			editJourneyLog.setLeftEngineCycleLastTime(selectedAircraft.getEngineOne().getCurrentEngineCycle());
			editJourneyLog.setRightEngineCycleLastTime(selectedAircraft.getEngineTwo().getCurrentEngineCycle());
		}
	}
	
	private void createJourneyLogList(){
		
	}
	
	public void saveJourneyLog(){
		
	}
	
	public void addNewTrip(){
		
	}

	public List<JourneyLog> getJourneyLogList() {
		return journeyLogList;
	}

	public void setJourneyLogList(List<JourneyLog> journeyLogList) {
		this.journeyLogList = journeyLogList;
	}

	public JourneyLog getEditJourneyLog() {
		return editJourneyLog;
	}

	public void setEditJourneyLog(JourneyLog editJourneyLog) {
		this.editJourneyLog = editJourneyLog;
	}

	public List<SelectItem> getAircraftSelectItemList() {
		return aircraftSelectItemList;
	}

	public void setAircraftSelectItemList(List<SelectItem> aircraftSelectItemList) {
		this.aircraftSelectItemList = aircraftSelectItemList;
	}

	public Aircraft getSelectedAircraft() {
		return selectedAircraft;
	}

	public void setSelectedAircraft(Aircraft selectedAircraft) {
		this.selectedAircraft = selectedAircraft;
	}

	public Long getAircraftID() {
		return aircraftID;
	}

	public void setAircraftID(Long aircraftID) {
		this.aircraftID = aircraftID;
	}

	public List<JourneyTrip> getJourneyTripList() {
		return journeyTripList;
	}

	public void setJourneyTripList(List<JourneyTrip> journeyTripList) {
		this.journeyTripList = journeyTripList;
	}
	
	
}
