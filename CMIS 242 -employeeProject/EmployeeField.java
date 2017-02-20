package employeeProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Student_DB.Student;

public abstract class EmployeeField {

    private EmployeeProfile employee;
    private ArrayList<String> list = new ArrayList<String>();

    private int monthlySalary;
    private int annualSales;
    private int stockPrice;

    private String year;
    private String employeeType;
    private String name;

    private int employeeCount;
    private int totalSalaries;
    private int averageSalary;

    private static final int RESET = 0;

    abstract String displayDataFieldsAll();

    public void createEmployeeProfiles() {

	if (getEmployeeType().equals("Executive"))
	    employee = new Executive(getName(), monthlySalary, stockPrice);

	if (getEmployeeType().equals("Employee"))
	    employee = new Employee(getName(), monthlySalary);

	if (getEmployeeType().equals("Salesman"))
	    employee = new Salesman(getName(), monthlySalary, annualSales);
    }

    public void setFields(String[] location) {

	year = location[0];
	employeeType = location[1];
	name = (location[2]);
	monthlySalary = Integer.parseInt(location[3]);
	annualSales = (getEmployeeType().equals("Salesman")
		? Integer.parseInt(location[4]) : 0);
	stockPrice = (getEmployeeType().equals("Executive")
		? Integer.parseInt(location[4]) : 0);
	createEmployeeProfiles();
    }

    public ArrayList<String> employeeProfile() {

	employeeCount++;
	totalSalaries();
	averageSalary(employeeCount);
	list.add(getYear() + employee.toString());

	return list;
    }

    public int totalSalaries() {

	totalSalaries = employee.annualSalary(monthlySalary) + totalSalaries;
	return totalSalaries;
    }

    public int averageSalary(int employeeCount) {
	averageSalary = (employeeCount == 0 ? totalSalaries / 1
		: totalSalaries / employeeCount);
	return averageSalary;
    }

    public ArrayList<String> getList() {

	return list;
    }

    public String getYear() {
	return year;
    }

    public String getName() {
	return name;
    }

    public String getEmployeeType() {
	return employeeType;
    }

    public int getEmployeeCount() {
	return employeeCount;
    }

    public int getTotalSalaries() {
	return totalSalaries;
    }

    public int getAverageSalary() {
	return averageSalary;
    }

    public ArrayList<String> sortEmployees(ArrayList<String> listSorted)

    {
	listSorted = employeeProfile();
	Collections.sort(listSorted);
	return listSorted;
    }

    public void resetEmployeeData() {

	getList().clear();
	totalSalaries = RESET;
	averageSalary = RESET;
	employeeCount = RESET;

    }

}
