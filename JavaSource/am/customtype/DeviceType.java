package am.customtype;

public enum DeviceType {
	AIRCRAFT("AIRCRAFT" ,"Aircraft"),
	ENGINE("ENGINE", "Engine"),
	PROPELLER("PROPELLER", "Propeller"),
	NONE("NONE", "None")
	;
	
	private String id;
	private String value;
	
	DeviceType(String aID, String aValue){
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
	
	public static DeviceType find(String aID){
		for (DeviceType type : DeviceType.values()) {
			if (type.id.equals(aID)){
				return type;
			}
		}
		return null;
	}
}
