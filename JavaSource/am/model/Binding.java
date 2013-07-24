package am.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.BindingStatus;
import am.customtype.DeviceType;

@Entity
@Table(name="AMBINDING")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Binding {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@Column(name="DEVICE_TYPE")
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;
	
	@Column(name="BINDING_STATUS")
	@Enumerated(EnumType.STRING)
	private BindingStatus bindingStatus = BindingStatus.BIND;
	
	@ManyToOne
	@JoinColumn(name="AIRCRAFT_ID", referencedColumnName="ID")
	private Aircraft aircraft;
	
	@ManyToOne
	@JoinColumn(name="ENGINE_ID", referencedColumnName="ID")
	private Engine engine;
	
	@ManyToOne
	@JoinColumn(name="PROPELLER_ID", referencedColumnName="ID")
	private Propeller propeller;
	
	@ManyToOne
	@JoinColumn(name="INSPECTION_ID", referencedColumnName="ID")
	private Inspection inspection;
	
	@ManyToOne
	@JoinColumn(name="CHAPTER_ID", referencedColumnName="ID")
	private Chapter chapter;
	
	@Column(name="CURRENT_LAND_CYC")
	private int currentLandingCycle;
	
	@Column(name="CURRENT_ENG_CYC")
	private int currentEngineCycle;
	
	@Column(name="CURRENT_PROP_CYC")
	private int currentPropellerCycle;
	
	@Column(name="CURRENT_FLIGHT_HOUR")
	private float currentFlightHour;
	
	@Column(name="PREV_LAND_CYC")
	private int previousLandingCycle;
	
	@Column(name="PREV_ENG_CYC")
	private int previousEngineCycle;
	
	@Column(name="PREV_PROP_CYC")
	private int previousPropellerCycle;
	
	@Column(name="PREV_FLIGHT_HOUR")
	private float previousFlightHour;
	
	@Column(name="NEXT_LAND_CYC")
	private int nextLandingCyccle;
	
	@Column(name="NEXT_ENG_CYC")
	private int nextEngineCycle;
	
	@Column(name="NEXT_PROP_CYC")
	private int nextPropellerCycle;
	
	@Column(name="NEXT_FLIGHT_HOUR")
	private float nextFlightHour;
	
	@Column(name="PREV_DUE_DATE")
	private Date previousDueDate;
	
	@Column(name="NEXT_DUE_DATE")
	private Date nextDueDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public BindingStatus getBindingStatus() {
		return bindingStatus;
	}
	public void setBindingStatus(BindingStatus bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Propeller getPropeller() {
		return propeller;
	}
	public void setPropeller(Propeller propeller) {
		this.propeller = propeller;
	}
	public Inspection getInspection() {
		return inspection;
	}
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public int getCurrentLandingCycle() {
		return currentLandingCycle;
	}
	public void setCurrentLandingCycle(int currentLandingCycle) {
		this.currentLandingCycle = currentLandingCycle;
	}
	public int getCurrentEngineCycle() {
		return currentEngineCycle;
	}
	public void setCurrentEngineCycle(int currentEngineCycle) {
		this.currentEngineCycle = currentEngineCycle;
	}
	public int getCurrentPropellerCycle() {
		return currentPropellerCycle;
	}
	public void setCurrentPropellerCycle(int currentPropellerCycle) {
		this.currentPropellerCycle = currentPropellerCycle;
	}
	public int getPreviousLandingCycle() {
		return previousLandingCycle;
	}
	public void setPreviousLandingCycle(int previousLandingCycle) {
		this.previousLandingCycle = previousLandingCycle;
	}
	public int getPreviousEngineCycle() {
		return previousEngineCycle;
	}
	public void setPreviousEngineCycle(int previousEngineCycle) {
		this.previousEngineCycle = previousEngineCycle;
	}
	public int getPreviousPropellerCycle() {
		return previousPropellerCycle;
	}
	public void setPreviousPropellerCycle(int previousPropellerCycle) {
		this.previousPropellerCycle = previousPropellerCycle;
	}
	public int getNextLandingCyccle() {
		return nextLandingCyccle;
	}
	public void setNextLandingCyccle(int nextLandingCyccle) {
		this.nextLandingCyccle = nextLandingCyccle;
	}
	public int getNextEngineCycle() {
		return nextEngineCycle;
	}
	public void setNextEngineCycle(int nextEngineCycle) {
		this.nextEngineCycle = nextEngineCycle;
	}
	public int getNextPropellerCycle() {
		return nextPropellerCycle;
	}
	public void setNextPropellerCycle(int nextPropellerCycle) {
		this.nextPropellerCycle = nextPropellerCycle;
	}
	public Date getPreviousDueDate() {
		return previousDueDate;
	}
	public void setPreviousDueDate(Date previousDueDate) {
		this.previousDueDate = previousDueDate;
	}
	public Date getNextDueDate() {
		return nextDueDate;
	}
	public void setNextDueDate(Date nextDueDate) {
		this.nextDueDate = nextDueDate;
	}
	public float getCurrentFlightHour() {
		return currentFlightHour;
	}
	public void setCurrentFlightHour(float currentFlightHour) {
		this.currentFlightHour = currentFlightHour;
	}
	public float getPreviousFlightHour() {
		return previousFlightHour;
	}
	public void setPreviousFlightHour(float previousFlightHour) {
		this.previousFlightHour = previousFlightHour;
	}
	public float getNextFlightHour() {
		return nextFlightHour;
	}
	public void setNextFlightHour(float nextFlightHour) {
		this.nextFlightHour = nextFlightHour;
	}
}
