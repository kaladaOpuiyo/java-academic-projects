package Student_DB;

public final class accessCondition {

    public static boolean studentInDataBase;

    public static boolean nameProvided;
    public static boolean majorProvided;
    public static boolean studentIDProvided;

    public static boolean studentIDNotInDatabase;

    public static int searchByMajor;
    public static int searchByStudentID;
    public static int searchByName;
    public static int notInRecords;
    public static int searchByNameMajor;

    final static int NO_RESULTS_FOUND = 0;
    final static int SEARCH_BY_MAJOR = 1;
    final static int SEARCH_BY_KEY = 2;
    final static int SEARCH_BY_NAME = 3;
    final static int SEARCH_BY_NAME_MAJOR = 4;

    public static void conditionsStudentID(Integer studentID) {

	studentIDProvided = studentID != null;

	studentIDNotInDatabase = !DataRequest.studentDataBase
		.containsKey(studentID);

	studentInDataBase = DataRequest.studentDataBase.containsKey(studentID);

    }

    public static void conditionsNameMajor(String name, String major) {

	nameProvided = name != null;

	majorProvided = major != null;

    }

    public static int searchBy(Integer studentID, String name, String major) {

	int search = NO_RESULTS_FOUND;

	searchByMajor = !studentIDProvided && majorProvided
		? search = SEARCH_BY_MAJOR : NO_RESULTS_FOUND;

	searchByStudentID = studentIDProvided ? search = SEARCH_BY_KEY
		: NO_RESULTS_FOUND;

	searchByName = !studentIDProvided && nameProvided
		? search = SEARCH_BY_NAME : NO_RESULTS_FOUND;

	searchByNameMajor = !studentIDProvided && nameProvided && majorProvided
		? search = SEARCH_BY_NAME_MAJOR : NO_RESULTS_FOUND;

	return search;

    }

}
