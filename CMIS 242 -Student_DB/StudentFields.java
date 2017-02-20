
package Student_DB;

import java.text.*;
import java.util.*;

abstract class StudentFields
	implements Comparator<StudentFields>, Comparable<StudentFields> {

    private Double gpa;
    private String name;
    private String major;
    private Integer totalNumberOfCredits = 0;
    private Integer totalQualityPoints = 0;
    private ArrayList<String> listCourse = new ArrayList<String>();
    private NumberFormat formatter = new DecimalFormat("#0.00");
    private Date dateTime = new Date();

    final static String ID = "ID";
    final static String NAME = "NAME";
    final static String MAJOR = "MAJOR";

    StudentFields(String name, String major) {

	this.name = name;
	this.major = major;
	gpaCalculator();

    }

    @Override // Compare Major
    public int compare(StudentFields o1, StudentFields o2) {

	return o1.getMajor().compareTo(o2.getMajor());
    }

    @Override // Compare Name
    public int compareTo(StudentFields o) {

	return (this.getName()).compareTo(o.getName());
    }

    private double gpaCalculator() {

	if (getListCourse().isEmpty()) {
	    setGpa(4.0);

	} else {

	    setGpa((double) (getTotalQualityPoints()
		    / (double) getTotalNumberOfCredits()));
	}

	return getGpa();
    }

    public ArrayList<String> courseCompleted(String dateOfCompletion,
	    String dateOfStart, int courseGrade, int creditHours,
	    String courseName) {

	SimpleDateFormat currentDateTime = new SimpleDateFormat(
		"MM/dd/yyyy h:mm:ss a");

	String formattedDate = currentDateTime.format(dateTime);

	setTotalNumberOfCredits(getTotalNumberOfCredits() + creditHours);

	setTotalQualityPoints(
		getTotalQualityPoints() + (courseGrade * creditHours));

	listCourse.add("\n Date Of Start:" + dateOfStart
		+ " Date Of Completion:" + dateOfCompletion + " Course Name:"
		+ courseName + " Course Grade:"
		+ GradeCalculator.gradeValues.getKey(courseGrade)
		+ " Credit Hours:" + creditHours + " Entry Date:"
		+ formattedDate);

	Collections.sort(listCourse);

	gpaCalculator();

	return getListCourse();
    }

    @Override
    public String toString() {

	return "\n STUDENT [ NAME:" + getName() + ",  MAJOR:" + getMajor()
		+ ",  TOTAL NUMBER OF CREDITS:" + getTotalNumberOfCredits()
		+ ",  TOTAL QUALITY POINTS:" + getTotalQualityPoints()
		+ ",  CURRENT GPA:" + formatter.format(getGpa()) + " ]\n"
		+ "LIST OF COURSES COMPLETED:\n" + getListCourse();

    }

    public double getGpa() {
	return gpa;
    }

    public void setGpa(Double gpa) {
	this.gpa = gpa;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setMajor(String major) {
	this.major = major;
    }

    public String getName() {
	return name;
    }

    public String getMajor() {
	return major;
    }

    public Integer getTotalNumberOfCredits() {
	return totalNumberOfCredits;
    }

    public void setTotalNumberOfCredits(Integer totalNumberOfCredits) {
	this.totalNumberOfCredits = totalNumberOfCredits;
    }

    public Integer getTotalQualityPoints() {
	return totalQualityPoints;
    }

    public void setTotalQualityPoints(Integer totalQualityPoints) {
	this.totalQualityPoints = totalQualityPoints;
    }

    public ArrayList<String> getListCourse() {
	return listCourse;
    }

    @Override
    public int hashCode() {

	final int prime = 31;
	int result = 1;

	result = prime * result + ((gpa == null) ? 0 : gpa.hashCode());
	result = prime * result + ((major == null) ? 0 : major.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());

	result = prime * result + ((getTotalNumberOfCredits() == null) ? 0
		: getTotalNumberOfCredits().hashCode());
	result = prime * result + ((getTotalQualityPoints() == null) ? 0
		: getTotalQualityPoints().hashCode());

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	StudentFields other = (StudentFields) obj;
	if (gpa == null) {
	    if (other.gpa != null)
		return false;
	} else if (!gpa.equals(other.gpa))
	    return false;
	if (major == null) {
	    if (other.major != null)
		return false;
	} else if (!major.equals(other.major))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (getTotalNumberOfCredits() == null) {
	    if (other.getTotalNumberOfCredits() != null)
		return false;
	} else if (!getTotalNumberOfCredits()
		.equals(other.getTotalNumberOfCredits()))
	    return false;
	if (getTotalQualityPoints() == null) {
	    if (other.getTotalQualityPoints() != null)
		return false;
	} else
	    if (!getTotalQualityPoints().equals(other.getTotalQualityPoints()))
	    return false;
	return true;
    }

}
