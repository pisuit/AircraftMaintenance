package am.model;

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

import am.customtype.DeviceType;

@Entity
@Table(name="AMPARTSCOMPABILITY")
@GenericGenerator(strategy="am.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class PartsCompability {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PARTS_ID", referencedColumnName="ID")
	private SpareParts spareParts;
	
	@ManyToOne
	@JoinColumn(name="AIRCRAFT_ID", referencedColumnName="ID")
	private Aircraft aircraft;
	
	@ManyToOne
	@JoinColumn(name="ENGINE_ID", referencedColumnName="ID")
	private Engine engine;
	
	@ManyToOne
	@JoinColumn(name="PROPELLER_ID", referencedColumnName="ID")
	private Propeller propeller;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SpareParts getSpareParts() {
		return spareParts;
	}
	public void setSpareParts(SpareParts spareParts) {
		this.spareParts = spareParts;
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
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
}
