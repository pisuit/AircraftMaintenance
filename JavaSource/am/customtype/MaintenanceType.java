package am.customtype;

public enum MaintenanceType {
	PREVENTIVE("PREVENTIVE", "Preventive"),
	CORRECTIVE("CORRECTIVE", "Corrective");
	
	private String id;
	private String value;
	
	MaintenanceType(String aID, String aValue){
		id = aID;
		value = aValue;
	}
	
	public String getID(){
		return id;
	}
	
	public String getValue(){
		return value;
	}

	public String toString() {
		return value;
	}
	
	public static MaintenanceType find(String aID){
		for (MaintenanceType type : MaintenanceType.values()) {
			if (type.id.equals(aID)){
				return type;
			}
		}
		return null;
	}
}
