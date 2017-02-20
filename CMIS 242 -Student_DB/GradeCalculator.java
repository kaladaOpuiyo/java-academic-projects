package Student_DB;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public final class GradeCalculator {
    public static Integer[] creditHours = { 3, 4 };
    public static String[] grade = { "A", "B", "C", "D", "F" };
    public static Integer[] gradeNumericValue = { 4, 3, 2, 1, 0 };
    public static BidiMap<String, Integer> gradeValues = new TreeBidiMap<>();

    public static BidiMap<String, Integer> assignGradeValue() {

	for (int i = 0; i < grade.length; i++)
	    gradeValues.put(grade[i], gradeNumericValue[i]);

	return gradeValues;

    }

}