package am.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="AMTHRESHOLD")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Threshold {
	
	@Id
	@GeneratedValue(generator="IDGENERATOR")
	@Column(name="ID")
	private Long id;
	
	@Column(name="DAYLEFT_INFO")
	private int dayLeftInfo;
	
	@Column(name="DAYLEFT_WARN")
	private int dayLeftWarn;
	
	@Column(name="DAYLEFT_DUE")
	private int dayLeftDue;
	
	@Column(name="HOURLEFT_INFO")
	private float hourLeftInfo;
	
	@Column(name="HOURLEFT_WARN")
	private float hourLeftWarn;
	
	@Column(name="HOURLEFT_DUE")
	private float hourLeftDue;
	
	@Column(name="LANDCYC_INFO")
	private int landingCycleLeftInfo;
	
	@Column(name="LANDCYC_WARN")
	private int landingCycleLeftWarn;
	
	@Column(name="LANDCYC_DUE")
	private int landingCycleLeftDue;
	
	@Column(name="ENGCYC_INFO")
	private int engineCycleLeftInfo;
	
	@Column(name="ENGCYC_WARN")
	private int engineCycleLeftWarn;
	
	@Column(name="ENGCYC_DUE")
	private int engineCycleLeftDue;
	
	@Column(name="PROPCYC_INFO")
	private int propellerCycleLeftInfo;
	
	@Column(name="PROPCYC_WARN")
	private int propellerCycleLeftWarn;
	
	@Column(name="PROPCYC_DUE")
	private int propellerCycleLeftDue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDayLeftInfo() {
		return dayLeftInfo;
	}
	public void setDayLeftInfo(int dayLeftInfo) {
		this.dayLeftInfo = dayLeftInfo;
	}
	public int getDayLeftWarn() {
		return dayLeftWarn;
	}
	public void setDayLeftWarn(int dayLeftWarn) {
		this.dayLeftWarn = dayLeftWarn;
	}
	public int getDayLeftDue() {
		return dayLeftDue;
	}
	public void setDayLeftDue(int dayLeftDue) {
		this.dayLeftDue = dayLeftDue;
	}
	public int getLandingCycleLeftInfo() {
		return landingCycleLeftInfo;
	}
	public void setLandingCycleLeftInfo(int landingCycleLeftInfo) {
		this.landingCycleLeftInfo = landingCycleLeftInfo;
	}
	public int getLandingCycleLeftWarn() {
		return landingCycleLeftWarn;
	}
	public void setLandingCycleLeftWarn(int landingCycleLeftWarn) {
		this.landingCycleLeftWarn = landingCycleLeftWarn;
	}
	public int getLandingCycleLeftDue() {
		return landingCycleLeftDue;
	}
	public void setLandingCycleLeftDue(int landingCycleLeftDue) {
		this.landingCycleLeftDue = landingCycleLeftDue;
	}
	public int getEngineCycleLeftInfo() {
		return engineCycleLeftInfo;
	}
	public void setEngineCycleLeftInfo(int engineCycleLeftInfo) {
		this.engineCycleLeftInfo = engineCycleLeftInfo;
	}
	public int getEngineCycleLeftWarn() {
		return engineCycleLeftWarn;
	}
	public void setEngineCycleLeftWarn(int engineCycleLeftWarn) {
		this.engineCycleLeftWarn = engineCycleLeftWarn;
	}
	public int getEngineCycleLeftDue() {
		return engineCycleLeftDue;
	}
	public void setEngineCycleLeftDue(int engineCycleLeftDue) {
		this.engineCycleLeftDue = engineCycleLeftDue;
	}
	public int getPropellerCycleLeftInfo() {
		return propellerCycleLeftInfo;
	}
	public void setPropellerCycleLeftInfo(int propellerCycleLeftInfo) {
		this.propellerCycleLeftInfo = propellerCycleLeftInfo;
	}
	public int getPropellerCycleLeftWarn() {
		return propellerCycleLeftWarn;
	}
	public void setPropellerCycleLeftWarn(int propellerCycleLeftWarn) {
		this.propellerCycleLeftWarn = propellerCycleLeftWarn;
	}
	public int getPropellerCycleLeftDue() {
		return propellerCycleLeftDue;
	}
	public void setPropellerCycleLeftDue(int propellerCycleLeftDue) {
		this.propellerCycleLeftDue = propellerCycleLeftDue;
	}
	public float getHourLeftInfo() {
		return hourLeftInfo;
	}
	public void setHourLeftInfo(float hourLeftInfo) {
		this.hourLeftInfo = hourLeftInfo;
	}
	public float getHourLeftWarn() {
		return hourLeftWarn;
	}
	public void setHourLeftWarn(float hourLeftWarn) {
		this.hourLeftWarn = hourLeftWarn;
	}
	public float getHourLeftDue() {
		return hourLeftDue;
	}
	public void setHourLeftDue(float hourLeftDue) {
		this.hourLeftDue = hourLeftDue;
	}
}
