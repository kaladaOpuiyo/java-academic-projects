package employeeProject;

public class Executive extends Employee {
    private int currentStockPrice;
    private int bonus;

    public Executive(String name, int monthlySalary, int currentStockPrice) {
	super(name, monthlySalary);

	this.currentStockPrice = currentStockPrice;
	bonus = ((currentStockPrice > 50) ? 30000 : 0);
    }

    public int annualSalary(int monthlySalary) {

	int salaryWholeYear = (super.annualSalary(monthlySalary));
	int salaryWholeYearBonus = salaryWholeYear + bonus;
	return salaryWholeYearBonus;
    }

    public String toString() {

	return " Executive" + super.toString() + "[Bonus= " + bonus + "]"+ "[Current Stock Price= "
		+ currentStockPrice + "]" ;
    }

}
