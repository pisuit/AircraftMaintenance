package am.customtype;

public enum BindingStatus {
	
	BIND("BIND","Bind"),
	UNBIND("UNBIND","Unbind");
	
	private String id;
	private String value;
	
	BindingStatus(String aID, String aValue){
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
	
	public static BindingStatus find(String aID){
		for (BindingStatus status : BindingStatus.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
