/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev425;

import javafx.scene.control.TextField;

/**
 *
 * @author Kalada Opuiyo
 */
public class LoginValidation {

    private boolean isValid = false;
    private int loginAttempt;

    private String displayResultView;

    public String validateData(TextField textField1, TextField textField2, int clicked) {

        loginAttempt = clicked;

        authenticate(textField1.getText(), textField2.getText());

        displayResults(isValid);

        return displayResultView;

    }

    /**
     * @param user the username entered
     * @param pword the password entered
     * @return isValid true for authenticated
     */
    private String authenticate(String user, String pword) {

        if (user.equalsIgnoreCase("sdevadmin")
                    && pword.equals("425!pass")) {

            displayResultView = "sysNotice";
            isValid = true;
        }

        return displayResultView;
    }

    private void displayResults(boolean validationResult) {

        if (validationResult == false && loginAttempt == 3) {

            loginAttempt = 0;
            displayResultView = "LockOut";

        } else if (validationResult == false) {

            displayResultView = "Again";

        }

    }

}
