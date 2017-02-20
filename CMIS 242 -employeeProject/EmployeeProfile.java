package employeeProject;

public abstract class EmployeeProfile {
    protected String name;
    protected int monthlySalary;

    public EmployeeProfile(String name, int monthlySalary) {
	this.name = name;
	this.monthlySalary = monthlySalary;
    }

    abstract int annualSalary(int monthlySalary);

    public abstract String toString();

}
