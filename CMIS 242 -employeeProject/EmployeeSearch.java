package employeeProject;

public class EmployeeSearch extends EmployeeField {

    public void searchColumns(String choice) {

	if (getEmployeeType().equalsIgnoreCase(choice)) {
	    sortEmployees(getList());
	}

	if (getYear().equals(choice)) {
	    sortEmployees(getList());
	}
	if (getName().contains(choice)) {
	    sortEmployees(getList());
	}

	if (choice.equalsIgnoreCase("join")) {
	    sortEmployees(getList());
	}

	if (choice.equalsIgnoreCase("exit")) {
	    System.exit(0);
	}

    }

    public String displayDataFieldsAll() {

	for (String displaySearchResult : getList()) {
	    System.out.println(displaySearchResult);

	}
	return ("\n[Employee Count  Result = " + getEmployeeCount() + "]")
		+ ("[Total Salaries Result = " + getTotalSalaries() + "]")
		+ ("[Average Salary Result = " + getAverageSalary() + "]\n");

    }
}