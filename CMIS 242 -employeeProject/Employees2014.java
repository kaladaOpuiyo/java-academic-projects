package employeeProject;


import java.util.ArrayList;
import java.util.Collections;

public class Employees2014 extends EmployeeField {

    private ArrayList<String> list2014 = new ArrayList<String>();

    public ArrayList<String> employeeProfile() {

	if (super.getYear().equals("2014")) {
	    list2014 = (super.employeeProfile());
	    Collections.sort(list2014);
	}
	return list2014;
    }

    public String displayDataFieldsAll() {

	for (String employees2014 : list2014) {
	    System.out.println(employees2014);
	}
	return ("\n[Employee Count 2014 = " + getEmployeeCount() + "]")
		+ ("[Total Salaries 2014 = " + getTotalSalaries() + "]")
		+ ("[Average Salary 2014 = " + getAverageSalary() + "]\n");

    }

}