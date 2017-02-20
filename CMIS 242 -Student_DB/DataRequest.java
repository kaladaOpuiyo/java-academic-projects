package Student_DB;

import java.awt.Component;

import javax.swing.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class DataRequest {

    private static Component StudentDatabaseGUI;

    public static HashMap<Integer, Student> studentDataBase = new HashMap<>();

    private static HashMap<Integer, Student> studentProfile(String name,
	    String major, Integer newStudentID) throws NameNotFound {

	accessCondition.conditionsNameMajor(name, major);

	if (accessCondition.nameProvided) {
            Student student = new Student(name, major);
            
            if (!accessCondition.majorProvided)
                student.setMajor("NOT DECLARED");
            
            studentDataBase.put(newStudentID, student);
        } else throw new NameNotFound();

	return studentDataBase;
    }

    private static <T, V> ArrayList<Entry<T, V>> searchDatabase_HashMap(
	    HashMap<T, V> map, Predicate<Entry<T, V>> searchCriteria) {

	ArrayList<Entry<T, V>> result = (ArrayList<Entry<T, V>>) map.entrySet()
		.stream().filter(searchCriteria).collect(Collectors.toList());

	if (result.isEmpty())
	    throw new NullPointerException();

	return result;

    }

    private static <T> Integer displayResult(String processRequested,
	    T result) {

	return JOptionPane.showConfirmDialog(StudentDatabaseGUI, result,
		processRequested, JOptionPane.CLOSED_OPTION);
    }

    public static void insert(Integer studentID, String name, String major,
	    String inserted)
		    throws StudentIDNotFound, StudentIDFound, NameNotFound {

	if (name.equalsIgnoreCase("ALL"))
	    displayResult(inserted, studentDataBase);

	else {

	    accessCondition.conditionsStudentID(studentID);

	    if (accessCondition.studentIDProvided) {
            } else {
                throw new StudentIDNotFound();
            }

	    if (accessCondition.studentInDataBase)
                throw new StudentIDFound();

	    else
                studentProfile(name, major, studentID);
	    displayResult(inserted, studentDataBase.get(studentID));
	}
    }

    public static void delete(Integer studentID, String deleting)
	    throws StudentIDNotFound {

	accessCondition.conditionsStudentID(studentID);

	if (accessCondition.studentIDProvided) {
        } else {
            throw new StudentIDNotFound();
        }

	if (accessCondition.studentIDNotInDatabase)
            throw new NullPointerException();

	else
            displayResult(deleting, studentDataBase.get(studentID));
	studentDataBase.remove(studentID);

    }

    public static void find(Integer studentID, String name, String major,
	    String findings) {

	accessCondition.conditionsStudentID(studentID);
	accessCondition.conditionsNameMajor(name, major);

	ArrayList<Entry<Integer, Student>> result = null;

	int selection;
        selection = accessCondition.searchBy(studentID, name, major);

	switch (selection) {

	case accessCondition.SEARCH_BY_KEY:
            /*accessCondition*/
	    if (null == studentDataBase.get(studentID))
		throw new NullPointerException();

	    else
		displayResult(findings, studentDataBase.get(studentID));

	    break;

	case accessCondition.SEARCH_BY_MAJOR:

	    result = searchDatabase_HashMap(studentDataBase,
		    displayAllStudents -> ((Entry<Integer, Student>) displayAllStudents)
			    .getValue().getMajor().equalsIgnoreCase(major));
	    break;

	case accessCondition.SEARCH_BY_NAME:

	    result = searchDatabase_HashMap(studentDataBase,
		    displayAllStudents -> ((Entry<Integer, Student>) displayAllStudents)
			    .getValue().getName().equalsIgnoreCase(name));
	    break;

	case accessCondition.SEARCH_BY_NAME_MAJOR:

	    result = searchDatabase_HashMap(studentDataBase,
		    displayAllStudents -> ((Entry<Integer, Student>) displayAllStudents)
			    .getValue().getMajor().equalsIgnoreCase(major)
			    && displayAllStudents.getValue().getName()
				    .equalsIgnoreCase(name));
	    break;

	default:

	    throw new NullPointerException();

	}

	if (!accessCondition.studentIDProvided)
	    displayResult(findings, result);

    }

    public static void update(Integer studentID, String name, String major,
	    String update) throws StudentIDNotFound, CourseNameNotFound {

	accessCondition.conditionsStudentID(studentID);
	accessCondition.conditionsNameMajor(name, major);

	Student studentChange = studentDataBase.get(studentID);

	if (!accessCondition.studentIDProvided)
	    throw new StudentIDNotFound();

	if (accessCondition.studentIDNotInDatabase)
	    throw new NullPointerException();

	if (accessCondition.nameProvided)
	    studentChange.setName(name);

	if (accessCondition.majorProvided)
	    studentChange.setMajor(major);

	int newCourse = CourseFieldsJOptionPane.newCourse();

	if (newCourse == JOptionPane.YES_OPTION) {

	    String courseName = CourseFieldsJOptionPane.courseName();

	    if (courseName.isEmpty())
		throw new CourseNameNotFound();

	    else {

		GradeCalculator.assignGradeValue();

		String dateOfStart = CourseFieldsJOptionPane.dateOfStart();

		String dateOfCompletion = CourseFieldsJOptionPane
			.dateOfCompletion();

		String gradeSelected = CourseFieldsJOptionPane.courseGrade();

		Integer creditSelected = CourseFieldsJOptionPane.creditHours();

		studentChange.courseCompleted(dateOfCompletion, dateOfStart,
			GradeCalculator.gradeValues.get(gradeSelected),
			creditSelected, courseName);
	    }

	}

	displayResult(update, studentDataBase.get(studentID));
    }

}