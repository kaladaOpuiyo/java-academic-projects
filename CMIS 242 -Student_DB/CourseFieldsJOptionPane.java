package Student_DB;

import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

abstract class CourseFieldsJOptionPane {

    private static Component StudentDatabaseGUI;

    public static String dateOfStart() throws HeadlessException {
	String dateOfStart = (String) JOptionPane
		.showInputDialog("Date Of Start:");
	return dateOfStart;
    }

    public static String dateOfCompletion() throws HeadlessException {
	String dateOfCompletion = (String) JOptionPane
		.showInputDialog("Date Of Completion:");
	return dateOfCompletion;
    }

    public static String courseName() throws HeadlessException {
	String courseName = (String) JOptionPane
		.showInputDialog("Enter Course:");
	return courseName;
    }

    public static int newCourse() throws HeadlessException {
	int newCourse = JOptionPane.showConfirmDialog(StudentDatabaseGUI,
		"Would you like to add a Course?", "New Course",
		JOptionPane.YES_NO_OPTION);
	return newCourse;
    }

    public static String courseGrade() throws HeadlessException {
	String gradeSelected = (String) JOptionPane.showInputDialog(
		StudentDatabaseGUI, "Choose Grade:", null,
		JOptionPane.QUESTION_MESSAGE, null, GradeCalculator.grade,
		GradeCalculator.grade[0]);
	return gradeSelected;
    }

    public static Integer creditHours() throws HeadlessException {
	Integer creditSelected = (Integer) JOptionPane.showInputDialog(
		StudentDatabaseGUI, "Choose Credit:", null,
		JOptionPane.QUESTION_MESSAGE, null, GradeCalculator.creditHours,
		GradeCalculator.creditHours[0]);
	return creditSelected;
    }
}
