package am.customtype;

public enum WorkCardStatus {
	PENDING("PENDING","Pending"),
	COMPLETED("COMPLETED","Completed");
	
	private String id;
	private String value;
	
	WorkCardStatus(String aID, String aValue){
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
	
	public static WorkCardStatus find(String aID){
		for (WorkCardStatus status : WorkCardStatus.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
