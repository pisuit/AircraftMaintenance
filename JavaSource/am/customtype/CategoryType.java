package am.customtype;

public enum CategoryType {
	WING("WING","Wing"),
	ENGINE("ENGINE","Engine"),
	WHEEL("WHEEL","Wheel");
	
	private String id;
	private String value;
	
	CategoryType(String aID, String aValue){
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
	
	public static CategoryType find(String aID){
		for (CategoryType status : CategoryType.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
