/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc_350_project_2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Kalada Opuiyo
 */
public final class AddressFile {

    public static ArrayList<String> register = new ArrayList<>();
    private static Path file;

    public static void registerAddress(String leftValue, String rightValue, String operator, String location) {
        
        register.add(convertToWord(operator) + " " + location + " " + leftValue + " " + rightValue);
    }

    public static void addToFile() throws IOException {

        file = Paths.get("registerAddress.txt");
        Files.write(file, register, Charset.forName("UTF-8"));

    }

    private static String convertToWord(String operator) {

        String word = operator.equals("+") ? "ADD"
                : operator.equals("-") ? "SUB"
                : operator.equals("*") ? "MUL"
                : "DIV";

        return word;
    }
}
