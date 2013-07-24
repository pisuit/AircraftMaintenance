package am.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import am.customtype.DataStatus;
import am.customtype.MaintenanceType;
import am.customtype.WorkCardStatus;

@Entity
@Table(name="AMWORKCARD")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class WorkCard {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@Column(name="WORKCARD_NUMBER")
	private String number;
	
	@Column(name="JOB_NUMBER")
	private String jobNumber;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="MAINTENANCE_TYPE")
	@Enumerated(EnumType.STRING)
	private MaintenanceType maintenanceType = MaintenanceType.CORRECTIVE;
	
	@ManyToOne
	@JoinColumn(name="ENGINE_ID", referencedColumnName="ID")
	private Engine engine;
	
	@ManyToOne
	@JoinColumn(name="AIRCRAFT_ID", referencedColumnName="ID")
	private Aircraft aircraft;
	
	@ManyToOne
	@JoinColumn(name="PROPELLER_ID", referencedColumnName="ID")
	private Propeller propeller;
	
	@Column(name="PERFORMER")
	private String performer;
	
	@Column(name="INSPECTOR")
	private String inspector;
	
	@Column(name="FLIGHT_HOUR")
	private float flightHour;
	
	@Column(name="ENGINE_CYCLE")
	private int engineCycle;
	
	@Column(name="PROPELLER_CYCLE")
	private int propellerCycle;
	
	@Column(name="LANDING_CYCLE")
	private int landingCycle;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.NORMAL;
	
	@Column(name="WORKCARD_STATUS")
	@Enumerated(EnumType.STRING)
	private WorkCardStatus workCardStatus = WorkCardStatus.PENDING;
	
	@Column(name="REQUIRE_REMARK")
	private String requireRemark;
	
	@Column(name="PERFORM_REMARK")
	private String performRemark;
	
	@OneToMany(mappedBy="workCard")
	private Set<WorkCardInspection> workCardInspections;
	
	@OneToMany(mappedBy="workCard")
	private Set<WorkCardParts> workCardParts;
	
	@Transient
	private List<WorkCardInspection> cardInspectionsList;
	
	@Transient
	private List<WorkCardParts> workCardPartsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Propeller getPropeller() {
		return propeller;
	}

	public void setPropeller(Propeller propeller) {
		this.propeller = propeller;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public int getEngineCycle() {
		return engineCycle;
	}

	public void setEngineCycle(int engineCycle) {
		this.engineCycle = engineCycle;
	}

	public int getPropellerCycle() {
		return propellerCycle;
	}

	public void setPropellerCycle(int propellerCycle) {
		this.propellerCycle = propellerCycle;
	}

	public int getLandingCycle() {
		return landingCycle;
	}

	public void setLandingCycle(int landingCycle) {
		this.landingCycle = landingCycle;
	}

	public DataStatus getStatus() {
		return status;
	}

	public void setStatus(DataStatus status) {
		this.status = status;
	}

	public WorkCardStatus getWorkCardStatus() {
		return workCardStatus;
	}

	public void setWorkCardStatus(WorkCardStatus workCardStatus) {
		this.workCardStatus = workCardStatus;
	}

	public String getRequireRemark() {
		return requireRemark;
	}

	public void setRequireRemark(String requireRemark) {
		this.requireRemark = requireRemark;
	}

	public String getPerformRemark() {
		return performRemark;
	}

	public void setPerformRemark(String performRemark) {
		this.performRemark = performRemark;
	}

	public Set<WorkCardParts> getWorkCardParts() {
		return workCardParts;
	}

	public void setWorkCardParts(Set<WorkCardParts> workCardParts) {
		this.workCardParts = workCardParts;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public List<WorkCardParts> getWorkCardPartsList() {
		workCardPartsList = new ArrayList<WorkCardParts>();
		
		if(workCardParts != null){
			workCardPartsList.addAll(workCardParts);
			return workCardPartsList;
		} else {
			return workCardPartsList;
		}	
	}

	public Set<WorkCardInspection> getWorkCardInspections() {
		return workCardInspections;
	}

	public void setWorkCardInspections(Set<WorkCardInspection> workCardInspections) {
		this.workCardInspections = workCardInspections;
	}

	public List<WorkCardInspection> getCardInspectionsList() {
		cardInspectionsList = new ArrayList<WorkCardInspection>();
		
		if(workCardInspections != null){
			cardInspectionsList.addAll(workCardInspections);
			return cardInspectionsList;
		} else {
			return cardInspectionsList;
		}
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public float getFlightHour() {
		return flightHour;
	}

	public void setFlightHour(float flightHour) {
		this.flightHour = flightHour;
	}
}
