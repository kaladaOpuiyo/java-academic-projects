package employeeProject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {
    
    private static int count;
    private static final int RESET = 0;
    private static String choice;
    private static RandomAccessFile inputStream;

    private static EmployeeField employees2014 = new Employees2014();
    private static EmployeeField employees2015 = new Employees2015();
    private static EmployeeField employeesSearch = new EmployeeSearch();

    public static void main(String[] newTxt) throws IOException {

	String fileLocation = newTxt[0];
	boolean running = true;
	while (running) {
	    runProgram(fileLocation);
	}
    }

    private static void runProgram(String fileLocation) throws IOException {

	System.out.print("(TO SEARCH,PLEASE SELECT:\n"
		+ "(2015), (2014), (YEARS),\n" + "(JOIN), (EMPLOYEE TYPE),\n"
		+ "(NAME(Characters allowed) or (EXIT)):");

	@SuppressWarnings("resource")
	String selection = new Scanner(System.in).next();
	choice = selection;

	if (choice.equalsIgnoreCase("years")) {
	    dataSearchEmployeeField(fileLocation);
	    displayDataForAllYears();

	} else {
	    dataSearchEmployeeField(fileLocation);
	    displaySearchResults();

	}
	resetFieldDataListAll(inputStream);

    }

    private static void dataSearchEmployeeField(String fileLocation)
	    throws IOException {

	File file = new File(fileLocation);
	inputStream = new RandomAccessFile(file, "r");

	ArrayList<String> lines = new ArrayList<String>();
	String line;
	String[] createLocations;

	while ((line = inputStream.readLine()) != null) {

	    lines.add(line);
	    createLocations = lines.get(count).split(" ");
	    employeeDataBuilder(createLocations);
	    count++;
	}
    }

    private static void employeeDataBuilder(String[] createLocations) {

	if (choice.equalsIgnoreCase("years")) {
	    employees2014.setFields(createLocations);
	    employees2014.employeeProfile();

	    employees2015.setFields(createLocations);
	    employees2015.employeeProfile();

	} else {

	    employeesSearch.setFields(createLocations);
	    ((EmployeeSearch) employeesSearch).searchColumns(choice);

	}

    }

    private static void displaySearchResults() {
	System.out.println(employeesSearch.displayDataFieldsAll());
    }

    private static void displayDataForAllYears() {
	System.out.println(employees2014.displayDataFieldsAll());
	System.out.println(employees2015.displayDataFieldsAll());
    }

    private static void resetFieldDataListAll(RandomAccessFile inputStream)
	    throws IOException {
	employeesSearch.resetEmployeeData();
	employees2014.resetEmployeeData();
	employees2015.resetEmployeeData();
	inputStream.seek(RESET);
	count = RESET;

    }

}
