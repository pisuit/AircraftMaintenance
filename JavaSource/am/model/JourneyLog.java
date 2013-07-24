package am.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.DataStatus;

@Entity
@Table(name = "AMJOURNEYLOG")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class JourneyLog {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="AIRCRAFT_ID", referencedColumnName="ID")
	private Aircraft aircraft;
	
	@Column(name="NATUREOFFLIGHT")
	private String natureOfFlight;
	
	@Column(name="ISSSUEDATE")
	private Date issueDate;
	
	@Column(name="CAPTAIN")
	private String captain;
	
	@Column(name="COPILOT")
	private String coPilot;
	
	@Column(name="FLIGHTENGINEER")
	private String flightEngineer;
	
	@Column(name="PANELOPERATOR")
	private String panelOperator;
	
	@Column(name="OBSERVER")
	private String observer;
	
	@Column(name="DEFECTS")
	private String defects;
	
	@Column(name="CORRECTIONS")
	private String corrections;
	
	@Column(name="AFLT")
	private float airframeLastTime;
	
	@Column(name="AFTT")
	private float airframeTotalTime;
	
	@Column(name="LELT")
	private float leftEngineLastTime;
	
	@Column(name="LETT")
	private float leftEngineTotalTime;
	
	@Column(name="RELT")
	private float rightEngineLastTime;
	
	@Column(name="RETT")
	private float rightEngineTotalTime;
	
	@Column(name="LPLT")
	private float leftPropellerLastTime;
	
	@Column(name="LPTT")
	private float leftPropellerTotalTime;
	
	@Column(name="RPLT")
	private float rightPropellerLastTime;
	
	@Column(name="RPTT")
	private float rightPropellerTotalTime;
	
	@Column(name="LCLT")
	private int landingCycleLastTime;
	
	@Column(name="LCTT")
	private int landingCycleTotalTime;
	
	@Column(name="LECLT")
	private int leftEngineCycleLastTime;
	
	@Column(name="LECTT")
	private int leftEngineCycleTotalTime;
	
	@Column(name="RECLT")
	private int rightEngineCycleLastTime;
	
	@Column(name="RECTT")
	private int rightEngineCycleTotalTime;
	
	@OneToMany(mappedBy="journeyLog")
	private List<JourneyTrip> journeyTrips;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@Transient
	private float newAirframeTime;
	
	@Transient
	private float newLeftEngineTime;
	
	@Transient
	private float newRightEngineTime;
	
	@Transient
	private float newLeftPropellerTime;
	
	@Transient
	private float newRightPropellerTime;
	
	@Transient
	private int newLandingCycles;
	
	@Transient
	private int newLeftEngineCycles;
	
	@Transient
	private int newRightEngineCycles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	public String getNatureOfFlight() {
		return natureOfFlight;
	}
	public void setNatureOfFlight(String natureOfFlight) {
		this.natureOfFlight = natureOfFlight;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public String getCoPilot() {
		return coPilot;
	}
	public void setCoPilot(String coPilot) {
		this.coPilot = coPilot;
	}
	public String getFlightEngineer() {
		return flightEngineer;
	}
	public void setFlightEngineer(String flightEngineer) {
		this.flightEngineer = flightEngineer;
	}
	public String getPanelOperator() {
		return panelOperator;
	}
	public void setPanelOperator(String panelOperator) {
		this.panelOperator = panelOperator;
	}
	public String getObserver() {
		return observer;
	}
	public void setObserver(String observer) {
		this.observer = observer;
	}
	public String getDefects() {
		return defects;
	}
	public void setDefects(String defects) {
		this.defects = defects;
	}
	public String getCorrections() {
		return corrections;
	}
	public void setCorrections(String corrections) {
		this.corrections = corrections;
	}
	public float getAirframeLastTime() {
		return airframeLastTime;
	}
	public void setAirframeLastTime(float airframeLastTime) {
		this.airframeLastTime = airframeLastTime;
	}
	public float getAirframeTotalTime() {
		return airframeTotalTime;
	}
	public void setAirframeTotalTime(float airframeTotalTime) {
		this.airframeTotalTime = airframeTotalTime;
	}
	public float getLeftEngineLastTime() {
		return leftEngineLastTime;
	}
	public void setLeftEngineLastTime(float leftEngineLastTime) {
		this.leftEngineLastTime = leftEngineLastTime;
	}
	public float getLeftEngineTotalTime() {
		return leftEngineTotalTime;
	}
	public void setLeftEngineTotalTime(float leftEngineTotalTime) {
		this.leftEngineTotalTime = leftEngineTotalTime;
	}
	public float getRightEngineLastTime() {
		return rightEngineLastTime;
	}
	public void setRightEngineLastTime(float rightEngineLastTime) {
		this.rightEngineLastTime = rightEngineLastTime;
	}
	public float getRightEngineTotalTime() {
		return rightEngineTotalTime;
	}
	public void setRightEngineTotalTime(float rightEngineTotalTime) {
		this.rightEngineTotalTime = rightEngineTotalTime;
	}
	public float getLeftPropellerLastTime() {
		return leftPropellerLastTime;
	}
	public void setLeftPropellerLastTime(float leftPropellerLastTime) {
		this.leftPropellerLastTime = leftPropellerLastTime;
	}
	public float getLeftPropellerTotalTime() {
		return leftPropellerTotalTime;
	}
	public void setLeftPropellerTotalTime(float leftPropellerTotalTime) {
		this.leftPropellerTotalTime = leftPropellerTotalTime;
	}
	public float getRightPropellerLastTime() {
		return rightPropellerLastTime;
	}
	public void setRightPropellerLastTime(float rightPropellerLastTime) {
		this.rightPropellerLastTime = rightPropellerLastTime;
	}
	public float getRightPropellerTotalTime() {
		return rightPropellerTotalTime;
	}
	public void setRightPropellerTotalTime(float rightPropellerTotalTime) {
		this.rightPropellerTotalTime = rightPropellerTotalTime;
	}
	public int getLandingCycleLastTime() {
		return landingCycleLastTime;
	}
	public void setLandingCycleLastTime(int landingCycleLastTime) {
		this.landingCycleLastTime = landingCycleLastTime;
	}
	public int getLandingCycleTotalTime() {
		return landingCycleTotalTime;
	}
	public void setLandingCycleTotalTime(int landingCycleTotalTime) {
		this.landingCycleTotalTime = landingCycleTotalTime;
	}
	public List<JourneyTrip> getJourneyTrips() {
		return journeyTrips;
	}
	public void setJourneyTrips(List<JourneyTrip> journeyTrips) {
		this.journeyTrips = journeyTrips;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	public int getLeftEngineCycleLastTime() {
		return leftEngineCycleLastTime;
	}
	public void setLeftEngineCycleLastTime(int leftEngineCycleLastTime) {
		this.leftEngineCycleLastTime = leftEngineCycleLastTime;
	}
	public int getLeftEngineCycleTotalTime() {
		return leftEngineCycleTotalTime;
	}
	public void setLeftEngineCycleTotalTime(int leftEngineCycleTotalTime) {
		this.leftEngineCycleTotalTime = leftEngineCycleTotalTime;
	}
	public int getRightEngineCycleLastTime() {
		return rightEngineCycleLastTime;
	}
	public void setRightEngineCycleLastTime(int rightEngineCycleLastTime) {
		this.rightEngineCycleLastTime = rightEngineCycleLastTime;
	}
	public int getRightEngineCycleTotalTime() {
		return rightEngineCycleTotalTime;
	}
	public void setRightEngineCycleTotalTime(int rightEngineCycleTotalTime) {
		this.rightEngineCycleTotalTime = rightEngineCycleTotalTime;
	}
	public float getNewAirframeTime() {
		return newAirframeTime;
	}
	public void setNewAirframeTime(float newAirframeTime) {
		this.newAirframeTime = newAirframeTime;
	}
	public float getNewLeftEngineTime() {
		return newLeftEngineTime;
	}
	public void setNewLeftEngineTime(float newLeftEngineTime) {
		this.newLeftEngineTime = newLeftEngineTime;
	}
	public float getNewRightEngineTime() {
		return newRightEngineTime;
	}
	public void setNewRightEngineTime(float newRightEngineTime) {
		this.newRightEngineTime = newRightEngineTime;
	}
	public float getNewLeftPropellerTime() {
		return newLeftPropellerTime;
	}
	public void setNewLeftPropellerTime(float newLeftPropellerTime) {
		this.newLeftPropellerTime = newLeftPropellerTime;
	}
	public float getNewRightPropellerTime() {
		return newRightPropellerTime;
	}
	public void setNewRightPropellerTime(float newRightPropellerTime) {
		this.newRightPropellerTime = newRightPropellerTime;
	}
	public int getNewLandingCycles() {
		return newLandingCycles;
	}
	public void setNewLandingCycles(int newLandingCycles) {
		this.newLandingCycles = newLandingCycles;
	}
	public int getNewLeftEngineCycles() {
		return newLeftEngineCycles;
	}
	public void setNewLeftEngineCycles(int newLeftEngineCycles) {
		this.newLeftEngineCycles = newLeftEngineCycles;
	}
	public int getNewRightEngineCycles() {
		return newRightEngineCycles;
	}
	public void setNewRightEngineCycles(int newRightEngineCycles) {
		this.newRightEngineCycles = newRightEngineCycles;
	}
}
