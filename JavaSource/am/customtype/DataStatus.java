package am.customtype;

public enum DataStatus {
	NORMAL("NORMAL","Normal"),
	DELETED("DELETED","Deleted");
	
	private String id;
	private String value;
	
	DataStatus(String aID, String aValue){
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
	
	public static DataStatus find(String aID){
		for (DataStatus status : DataStatus.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
