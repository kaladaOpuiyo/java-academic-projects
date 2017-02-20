package cmsc_350_project_3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Exceptions.*;

import javax.swing.JOptionPane;

/**
 *
 * @author Kalada Opuiyo
 */
public class CheckData {

    public void confirmNoExceptions(String text, String type, BinarySearchTree<String> unsrtedList) {

        String[] data = text.split("\\s+");

        try {

            for (String txt : data) {

                if ("Fraction".equals(type)
                        && !txt.matches("(\\d{1,2}\\/\\d{1,2})+")
                        && !txt.matches("(^[0-9]+$)")) {
                    throw new NumberFormatExpression();
                }
                if ("Fraction".equals(type)
                        && txt.matches("(^[0-9]+$)")) {
                    throw new IncorrectTypeException(txt);
                }

                if ("Integer".equals(type)
                        && !txt.matches("(^[0-9]+$)")
                        && !txt.matches("(\\d{1,2}\\/\\d{1,2})+")) {
                    throw new NumberFormatExpression();
                }

                if ("Integer".equals(type)
                        && txt.matches("(\\d{1,2}\\/\\d{1,2})+")) {
                    throw new IncorrectTypeException(txt);
                }

            }

            unsrtedList.initializeTree(text);

            unsrtedList.insert(unsrtedList.root, type);

        } catch (NumberFormatExpression nfe) {

            JOptionPane.showMessageDialog(null, "Non numeric input\n");

        } catch (IncorrectTypeException ict) {

            JOptionPane.showMessageDialog(null, "Incorrect type "
                    + "(" + ict.getMessage() + ")" + " for selected Numeric Type");

        }

    }

}
