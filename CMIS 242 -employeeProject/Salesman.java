package employeeProject;

public class Salesman extends Employee {

    private int annualSales;
    private int commission;

    public Salesman(String name, int monthlySalary, int annualSales) {
	super(name, monthlySalary);

	this.annualSales = annualSales;
	commission = ((int) (0.02 * annualSales < 20000
		? (int) (0.02 * annualSales) : 20000));
    }

    @Override
    public int annualSalary(int monthlySalary) {

	int salaryWholeYear = (super.annualSalary(monthlySalary));
	int salaryWholeYearCommission = salaryWholeYear + commission;
	return salaryWholeYearCommission;
    }

    public String toString() {

	return " Sales" + super.toString() + "[Commission= " + commission + "]"+"[Annual Sales= " + annualSales
		+ "]" ;
    }

}
