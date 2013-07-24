package am.model;

import java.util.List;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.DataStatus;
import am.customtype.Status;

@Entity
@Table(name = "AMPROPELLER")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Propeller {

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "IDGENERATOR")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PROPELLER_NUMBER")
	private String number;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@Column(name = "MANUFAC_YEAR")
	private String manufacYear;

	@Column(name = "MANUFAC_PLANT")
	private String manufacPlant;

	@Column(name = "CURRENT_PC")
	private int currentPropellerCycle;

	@Column(name = "CURRENT_FH")
	private float currentFlightHour;

	@OneToMany(mappedBy = "propellerOne")
	private Set<Aircraft> aircraftPropellerOne;

	@OneToMany(mappedBy = "propellerTwo")
	private Set<Aircraft> aircraftPropellerTwo;

	@Column(name = "CURRENT_STATUS")
	@Enumerated(EnumType.STRING)
	private Status status = Status.READY;

	@Column(name = "DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getManufacPlant() {
		return manufacPlant;
	}

	public void setManufacPlant(String manufacPlant) {
		this.manufacPlant = manufacPlant;
	}

	public int getCurrentPropellerCycle() {
		return currentPropellerCycle;
	}

	public void setCurrentPropellerCycle(int currentPropellerCycle) {
		this.currentPropellerCycle = currentPropellerCycle;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getUniqeName() {
		return model + " " + serialNumber;
	}

	public Set<Aircraft> getAircraftPropellerOne() {
		return aircraftPropellerOne;
	}

	public void setAircraftPropellerOne(Set<Aircraft> aircraftPropellerOne) {
		this.aircraftPropellerOne = aircraftPropellerOne;
	}

	public Set<Aircraft> getAircraftPropellerTwo() {
		return aircraftPropellerTwo;
	}

	public void setAircraftPropellerTwo(Set<Aircraft> aircraftPropellerTwo) {
		this.aircraftPropellerTwo = aircraftPropellerTwo;
	}
	
	public String toString(){
		return model + " " + serialNumber;
	}

	public float getCurrentFlightHour() {
		return currentFlightHour;
	}

	public void setCurrentFlightHour(float currentFlightHour) {
		this.currentFlightHour = currentFlightHour;
	}

}
