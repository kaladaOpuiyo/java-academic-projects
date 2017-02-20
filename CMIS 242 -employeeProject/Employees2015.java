package employeeProject;

import java.util.ArrayList;
import java.util.Collections;


public class Employees2015 extends EmployeeField {

    private ArrayList<String> list2015 = new ArrayList<String>();

    public ArrayList<String> employeeProfile() {

	if (super.getYear().equals("2015")) {
	    list2015 = (super.employeeProfile());
	    Collections.sort(list2015);
	}
	return list2015;
    }

    public String displayDataFieldsAll() {

	for (String employees2015 : list2015) {
	    System.out.println(employees2015);
	}
	return ("\n[Employee Count 2015 = " + getEmployeeCount() + "]")
		+ ("[Total Salaries 2015 = " + getTotalSalaries() + "]")
		+ ("[Average Salary 2015 = " + getAverageSalary() + "]\n");

    }
}
