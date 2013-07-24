package am.datamodel;

import am.customtype.DeviceType;
import am.model.Aircraft;
import am.model.Binding;
import am.model.Chapter;
import am.model.Engine;
import am.model.Inspection;
import am.model.Propeller;

public class ScheduleDataModel {
	private Long id;
	private Chapter chapter;
	private Inspection inspection;
	private int dayLeft;
	private float hourLeft;
	private int landingCycleLeft;
	private int engineCycleLeft;
	private int propellerCycleLeft;
	private Binding binding;
	private DeviceType deviceType;
	private Aircraft aircraft;
	private Engine engine;
	private Propeller propeller;
	private String deviceName;
	
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
	public Inspection getInspection() {
		return inspection;
	}
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	public int getDayLeft() {
		return dayLeft;
	}
	public void setDayLeft(int dayLeft) {
		this.dayLeft = dayLeft;
	}
	public int getLandingCycleLeft() {
		return landingCycleLeft;
	}
	public void setLandingCycleLeft(int landingCycleLeft) {
		this.landingCycleLeft = landingCycleLeft;
	}
	public int getEngineCycleLeft() {
		return engineCycleLeft;
	}
	public void setEngineCycleLeft(int engineCycleLeft) {
		this.engineCycleLeft = engineCycleLeft;
	}
	public int getPropellerCycleLeft() {
		return propellerCycleLeft;
	}
	public void setPropellerCycleLeft(int propellerCycleLeft) {
		this.propellerCycleLeft = propellerCycleLeft;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Binding getBinding() {
		return binding;
	}
	public void setBinding(Binding binding) {
		this.binding = binding;
	}
	public float getHourLeft() {
		return hourLeft;
	}
	public void setHourLeft(float hourLeft) {
		this.hourLeft = hourLeft;
	}
}
