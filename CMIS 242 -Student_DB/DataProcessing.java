package Student_DB;

import javax.swing.*;

public interface DataProcessing {

    final static int INSERT = 0;
    final static int DELETE = 1;
    final static int FIND = 2;
    final static int UPDATE = 3;
    final static String PROCESS_REQUEST = "PROCESS REQUEST";
    final static int RESET = 0;

    final static String INSERTING = "INSERT";
    final static String DELETING = "DELETE";
    final static String FINDING = "FIND";
    final static String UPDATING = "UPDATE";

    public void processRequest(JTextField textField1, JTextField textField2,
	    JTextField textField3, JComboBox databaseActionCombo)
		    throws StudentIDNotFound, StudentIDFound,
		    CourseNameNotFound, NameNotFound;

}
