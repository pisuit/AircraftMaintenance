package am.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AMJOURNEYTRIP")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class JourneyTrip {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@Column(name="FROM")
	private String from;
	
	@Column(name="TO")
	private String to;
	
	@Column(name="START")
	private Date start; 
	
	@Column(name="TAKEOFF")
	private Date takeOff;
	
	@Column(name="LANDING")
	private Date landing;
	
	@Column(name="STOP")
	private Date stop;
	
	@Column(name="AIRBORNTIME")
	private float airbornTime;
	
	@Column(name="BLOCKTIME")
	private float blockTime;
	
	@Column(name="NOLADING")
	private int numberOfLanding;
	
	@Column(name="LEC")
	private int leftEngineCycle;
	
	@Column(name="REC")
	private int rightEngineCycle;
	
	@Column(name="FUELADDED")
	private float fuelAdded;
	
	@Column(name="FUELTAKEOFF")
	private float fuelTakeOff;
	
	@Column(name="ORDER")
	private int order = 1;
	
	@ManyToOne
	@JoinColumn(name="JOURNEYLOG_ID", referencedColumnName="ID")
	private JourneyLog journeyLog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getTakeOff() {
		return takeOff;
	}

	public void setTakeOff(Date takeOff) {
		this.takeOff = takeOff;
	}

	public Date getLanding() {
		return landing;
	}

	public void setLanding(Date landing) {
		this.landing = landing;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public float getAirbornTime() {
		return airbornTime;
	}

	public void setAirbornTime(float airbornTime) {
		this.airbornTime = airbornTime;
	}

	public float getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(float blockTime) {
		this.blockTime = blockTime;
	}

	public int getNumberOfLanding() {
		return numberOfLanding;
	}

	public void setNumberOfLanding(int numberOfLanding) {
		this.numberOfLanding = numberOfLanding;
	}

	public int getLeftEngineCycle() {
		return leftEngineCycle;
	}

	public void setLeftEngineCycle(int leftEngineCycle) {
		this.leftEngineCycle = leftEngineCycle;
	}

	public int getRightEngineCycle() {
		return rightEngineCycle;
	}

	public void setRightEngineCycle(int rightEngineCycle) {
		this.rightEngineCycle = rightEngineCycle;
	}

	public float getFuelAdded() {
		return fuelAdded;
	}

	public void setFuelAdded(float fuelAdded) {
		this.fuelAdded = fuelAdded;
	}

	public float getFuelTakeOff() {
		return fuelTakeOff;
	}

	public void setFuelTakeOff(float fuelTakeOff) {
		this.fuelTakeOff = fuelTakeOff;
	}

	public JourneyLog getJourneyLog() {
		return journeyLog;
	}

	public void setJourneyLog(JourneyLog journeyLog) {
		this.journeyLog = journeyLog;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
