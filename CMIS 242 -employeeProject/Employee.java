package employeeProject;

public class Employee extends EmployeeProfile {

    public Employee(String name, int monthlySalary) {
	super(name, monthlySalary);

    }

    int annualSalary(int monthlySalary) {

	int salaryWholeYear = 12 * monthlySalary;

	return salaryWholeYear;
    }

    public String toString() {

	return " Employee [Name= " + name + "][Monthly Salary= "
		+ monthlySalary + "]" + "[Annual Salary= "
		+ annualSalary(monthlySalary) + "]";

    }

}
