package am.customtype;

public enum InspectionType {
	MINOR("MINOR","Minor"),
	PHASE_1("PHASE_1","Phase 1"),
	PHASE_2("PHASE_2","Phase 2"),
	PHASE_3("PHASE_3","Phase 3"),
	PHASE_4("PHASE_4","Phase 4"),
	HOT_SECTION("HOT_SECTION","Hot Section"),
	OVERHAUL("OVERHAUL","Overhaul")
	;
	
	private String id;
	private String value;
	
	InspectionType(String aID, String aValue){
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
	
	public static InspectionType find(String aID){
		for (InspectionType type : InspectionType.values()) {
			if (type.id.equals(aID)){
				return type;
			}
		}
		return null;
	}
}
