package Student_DB;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class DataRequestExecution implements DataProcessing {

    public void excuteDataRequestPanel(JTextField textField1,
	    JTextField textField2, JTextField textField3,
	    JComboBox comboxSelection) {

	try {

	    processRequest(textField1, textField2, textField3, comboxSelection);

	} catch (NullPointerException e1) {

	    JOptionPane.showConfirmDialog(null, "No records found in System",
		    "No records Found", JOptionPane.CLOSED_OPTION);

	} catch (StudentIDNotFound e2) {

	    JOptionPane.showConfirmDialog(null, "Please enter a Student ID",
		    "Student ID not provided", JOptionPane.CLOSED_OPTION);

	} catch (StudentIDFound e3) {

	    JOptionPane.showConfirmDialog(null, "Student ID exist in System",
		    "Student ID Found", JOptionPane.CLOSED_OPTION);

	} catch (NumberFormatException e4) {

	    JOptionPane.showConfirmDialog(null, "Please enter a number",
		    "Number Error Error", JOptionPane.CLOSED_OPTION);

	} catch (RuntimeException e5) {

	    JOptionPane.showConfirmDialog(null,
		    "A required Field has not been included", "Field Error",
		    JOptionPane.CLOSED_OPTION);

	} catch (CourseNameNotFound e6) {

	    JOptionPane.showConfirmDialog(null, "Couse name required",
		    "Course Name Not Found", JOptionPane.CLOSED_OPTION);

	} catch (NameNotFound e7) {

	    JOptionPane.showConfirmDialog(null, "A Student Name is required",
		    "Name Not Found", JOptionPane.CLOSED_OPTION);

	}

    }

    public void processRequest(JTextField textField1, JTextField textField2,
	    JTextField textField3, JComboBox comboxSelection)
		    throws StudentIDNotFound, StudentIDFound,
		    CourseNameNotFound, NameNotFound {

	String name = textField2.getText().isEmpty() ? null
		: textField2.getText();

	String major = textField3.getText().isEmpty() ? null
		: textField3.getText();

	Integer studentID = textField1.getText().isEmpty() ? null
		: Integer.valueOf(textField1.getText());

	switch (comboxSelection.getSelectedIndex()) {

	case INSERT:

	    DataRequest.insert(studentID, name, major, INSERTING);

	    break;

	case DELETE:

	    DataRequest.delete(studentID, DELETING);
	    break;

	case FIND:

	    DataRequest.find(studentID, name, major, FINDING);
	    break;

	case UPDATE:

	    DataRequest.update(studentID, name, major, UPDATING);
	    break;

	default:

	    break;

	}

    }

}