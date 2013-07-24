package am.customtype;

public enum Status {
	READY("READY","Ready"),
	UNDER_MAINTENANCE("UNDER_MAINTENANCE","Under Maintenance"),
	ATTACHED("ATTACHED","Attached"),
	OUTSOURCE_REPAIR("OUTSOURCE_REPAIR","Outsource Repair");
	
	private String id;
	private String value;
	
	Status(String aID, String aValue){
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
	
	public static Status find(String aID){
		for (Status status : Status.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
