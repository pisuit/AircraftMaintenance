package am.model;

import java.util.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.DataStatus;
import am.customtype.Status;

@Entity
@Table(name = "AMAIRCRAFT")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Aircraft {

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "IDGENERATOR")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "REGISTER")
	private String register;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@Column(name = "NUMBER_OF_SEAT")
	private int numberOfSeat;

	@Column(name = "MANUFAC_YEAR")
	private String manufacYear;

	@Column(name = "MANUFAC_PLANT")
	private String manufacPlant;

	@ManyToOne
	@JoinColumn(name = "ENGINE_ONE_ID", referencedColumnName = "ID")
	private Engine engineOne;

	@ManyToOne
	@JoinColumn(name = "ENGINE_TWO_ID", referencedColumnName = "ID")
	private Engine engineTwo;

	@ManyToOne
	@JoinColumn(name = "PROPELLER_ONE_ID", referencedColumnName = "ID")
	private Propeller propellerOne;

	@ManyToOne
	@JoinColumn(name = "PROPELLER_TWO_ID", referencedColumnName = "ID")
	private Propeller propellerTwo;

	@Column(name = "CURRENT_STATUS")
	@Enumerated(EnumType.STRING)
	private Status status = Status.READY;

	@ManyToOne
	@JoinColumn(name = "CURRENT_LOCATION_ID", referencedColumnName = "ID")
	private Location currentLocation;

	@Column(name = "CURRENT_FH")
	private float currentFlightHour;

	@Column(name = "CURRENT_LC")
	private int currentLandingCycle;

	@Column(name = "DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@OneToMany(mappedBy="aircraft")
	private Set<Binding> bindings;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Binding> getBindings() {
		return bindings;
	}

	public void setBindings(Set<Binding> bindings) {
		this.bindings = bindings;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public String getManufacPlant() {
		return manufacPlant;
	}

	public void setManufacPlant(String manufacPlant) {
		this.manufacPlant = manufacPlant;
	}

	public Engine getEngineOne() {
		return engineOne;
	}

	public void setEngineOne(Engine engineOne) {
		this.engineOne = engineOne;
	}

	public Engine getEngineTwo() {
		return engineTwo;
	}

	public void setEngineTwo(Engine engineTwo) {
		this.engineTwo = engineTwo;
	}

	public Propeller getPropellerOne() {
		return propellerOne;
	}

	public void setPropellerOne(Propeller propellerOne) {
		this.propellerOne = propellerOne;
	}

	public Propeller getPropellerTwo() {
		return propellerTwo;
	}

	public void setPropellerTwo(Propeller propellerTwo) {
		this.propellerTwo = propellerTwo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getCurrentLandingCycle() {
		return currentLandingCycle;
	}

	public void setCurrentLandingCycle(int currentLandingCycle) {
		this.currentLandingCycle = currentLandingCycle;
	}

	public DataStatus getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getManufacYear() {
		return manufacYear;
	}

	public void setManufacYear(String manufacYear) {
		this.manufacYear = manufacYear;
	}

	public float getCurrentFlightHour() {
		return currentFlightHour;
	}

	public void setCurrentFlightHour(float currentFlightHour) {
		this.currentFlightHour = currentFlightHour;
	}

}
