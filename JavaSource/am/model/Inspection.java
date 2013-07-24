package am.model;

import java.util.ArrayList;
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
import am.customtype.InspectionType;

@Entity
@Table(name="AMINSPECTION")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Inspection {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="CHAPTER_ID", referencedColumnName="ID")
	private Chapter chapter;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="LIMIT_LAND_CYC")
	private int limitedLandingCycle;
	
	@Column(name="LIMIT_ENG_CYC")
	private int limitedEngineCycle;
	
	@Column(name="LIMIT_PROPEL_CYC")
	private int limitedPropellerCycle;
	
	@Column(name="LIMIT_FLIGHT_HOUR")
	private float limitedFlightHour;
	
	@Column(name="LIMIT_TIME")
	private int limitedTime;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private InspectionType type = InspectionType.MINOR;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@OneToMany(mappedBy="inspection")
	private Set<SpareParts> partList;
	
	@Transient
	private List<SpareParts> partAsList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLimitedLandingCycle() {
		return limitedLandingCycle;
	}
	public void setLimitedLandingCycle(int limitedLandingCycle) {
		this.limitedLandingCycle = limitedLandingCycle;
	}
	public int getLimitedEngineCycle() {
		return limitedEngineCycle;
	}
	public void setLimitedEngineCycle(int limitedEngineCycle) {
		this.limitedEngineCycle = limitedEngineCycle;
	}
	public int getLimitedPropellerCycle() {
		return limitedPropellerCycle;
	}
	public void setLimitedPropellerCycle(int limitedPropellerCycle) {
		this.limitedPropellerCycle = limitedPropellerCycle;
	}
	public int getLimitedTime() {
		return limitedTime;
	}
	public void setLimitedTime(int limitedTime) {
		this.limitedTime = limitedTime;
	}
	public InspectionType getType() {
		return type;
	}
	public void setType(InspectionType type) {
		this.type = type;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	public Set<SpareParts> getPartList() {
		return partList;
	}
	public void setPartList(Set<SpareParts> partList) {
		this.partList = partList;
	}
	public List<SpareParts> getPartAsList() {
		if(partList != null){
			partAsList = new ArrayList<SpareParts>();
			partAsList.addAll(partList);
			return partAsList;
		} else {
			return partAsList;
		}	
	}
	public float getLimitedFlightHour() {
		return limitedFlightHour;
	}
	public void setLimitedFlightHour(float limitedFlightHour) {
		this.limitedFlightHour = limitedFlightHour;
	}
}
