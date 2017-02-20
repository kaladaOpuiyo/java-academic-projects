package sdev425;

import com.twilio.sdk.TwilioRestException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.BasicConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Kalada Opuiyo
 */
public class SDEV425HW2 extends Application {

    private final Text actiontargetSet;
    private Button login, backToLogin, accept, cancel, sendPhoneMessage, cancelPhoneMessage, submitMFACode, auditLogFile;
    private Text scenetitle;
    private Label userName, pw, phoneLabel, authCodeLabel;
    private TextField userTextField, phone, authCode;
    private PasswordField pwBox;
    private GridPane grid;
    private String authCodeNum;
    private Boolean timeout;
    private int clicked, id;

    private Button logOut;
    private PrintWriter writer;
    private EntityManager em;
    private TransactionLog trans;
    private LogController action;
    private Label auditLogRequest;

    public SDEV425HW2() {

        this.id = 1;
        this.clicked = 0;
        this.timeout = false;
        this.actiontargetSet = new Text();

    }

    @Override
    public void start(Stage primaryStage) {

        mainGrid(primaryStage);

    }

    //Main Grid Platform
    private void mainGrid(Stage primaryStage) {

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        primaryStage.setTitle(Tag.MAIN_TITLE);

        grid.setHgap(10);
        grid.setVgap(10);

        displayView(Tag.DISPLAY_DEFAULT);

        setScene(primaryStage, grid);

    }

    //Set Scene
    private void setScene(Stage primaryStage, GridPane grid) {

        // Set the size of Scene
        Scene scene = new Scene(grid, 700, 400);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    //Display Controller
    public void displayView(String display) {

        grid.getChildren().clear();

        switch (display) {

            case "sysNotice":

                registerTransaction("System Notice Request ");
                systemUserNotice();

                break;

            case "multiFactAuthView":

                //registerTransaction("MFA Request");
                multiFactAuthView();

                break;

            case "LockOut":

                registerTransaction("LockOut");
                displayLockout();

                break;

            case "LogIn":

                logUserIn();

                break;

            default:

                displayLogin();
                registerTransaction(Tag.DISPLAY_DEFAULT);

                break;

        }

    }

    //Login check against credentials 
    private void logUserIn() {

        if (authCode.getText() == null ? authCodeNum == null : authCode.getText().equals(authCodeNum)) {

            Text welcome = new Text("Welcome " + userTextField.getText() + "!");

            auditLogFile = new Button(Tag.SUBMIT);

            logOut = new Button("Log Out");
            auditLogRequest = new Label("Audit Log Request");

            grid.add(auditLogRequest, 0, 2);

            grid.add(auditLogFile, 1, 2);
            grid.add(logOut, 1, 3);

            auditLogFile.setDisable(false);

            auditLogFile.setOnAction((ActionEvent t) -> {

                auditLog();

            });

            logOut.setOnAction((ActionEvent t) -> {

                registerTransaction("Logged user out");
                //Log-out Action
                displayView(Tag.DISPLAY_DEFAULT);

            });

            grid.add(welcome, 0, 0, 2, 1);
            registerTransaction("User Logged In");

        } else {

            displayView("multiFactAuthView");
            actiontargetDisplay("Again");
            registerTransaction("Failed Login attempt");
        }
    }

    //Login Display
    private void displayLogin() {

        //Screen Title
        scenetitle = new Text(Tag.SCREEN_TITLE);

        //TextBox Tag
        userName = new Label(Tag.TXTBOX_TITLE_1);
        pw = new Label(Tag.TXTBOX_TITLE_2);

        // Create Login Button
        login = new Button(Tag.LOGIN);

        // Create Textfield
        userTextField = new TextField();

        // Create Passwordfield
        pwBox = new PasswordField();

        scenetitle = new Text(Tag.SCREEN_TITLE);

        //Add Components
        grid.add(scenetitle, 0, 0, 2, 1);
        // Add label to grid 0,1
        grid.add(userName, 0, 1);
        // Add textfield to grid 1,1
        grid.add(userTextField, 1, 1);
        // Add label to grid 0,2
        grid.add(pw, 0, 2);
        // Add Password field to grid 1,2
        grid.add(pwBox, 1, 2);
        // Add button to grid 1,4
        grid.add(login, 1, 4);

        buttonActionOnClick();

    }

    //login button action 
    private void buttonActionOnClick() {

        new ButtonAction(userTextField, pwBox);

    }

    //Inner class used to validate 
    public final class ButtonAction extends LoginValidation {

        ButtonAction(TextField textField1, TextField textField2) {

            login.setOnAction((ActionEvent t) -> {

                clicked++;

                if (clicked == 3) {

                    displayView(validateData(textField1, textField2, clicked));

                    clicked = 0;

                } else {

                    displayView(validateData(textField1, textField2, clicked));
                    actiontargetDisplay(validateData(textField1, textField2, clicked));

                }

            });

        }

    }

    //Alert Messages
    private void actiontargetDisplay(String display) {

        actiontargetSet.setText(" ");
        actiontargetSet.setVisible(true);

        switch (display) {

            case "Sucess":

                actiontargetSet.setFill(Color.GREEN);
                actiontargetSet.setText("Request Was Sucessfully Sent");

                break;

            case "Again":

                actiontargetSet.setFill(Color.FIREBRICK);
                actiontargetSet.setText("Please try again.");

                break;

            case "Phone":

                actiontargetSet.setFill(Color.FIREBRICK);
                actiontargetSet.setText("Please enter a valid Phone");

                break;

            default:

                actiontargetSet.setVisible(false);

                break;
        }

        grid.add(actiontargetSet, 1, 8);

    }

    //AC-7 - UNSUCCESSFUL LOGON ATTEMPTS
    private void displayLockout() {

        Text lockout = new Text("Login has been suspended due to 3 unsucessful attempts, "
                + "\n Please wait 10 secs and Try again");

        //Enter Admin Reset Code 
        grid.add(lockout, 0, 0, 2, 1);

        buttonDisplay();
    }

    //Button Function, Time-out for 10 secs, button hide
    private void buttonDisplay() {

        //Return to Login Screen
        backToLogin = new Button(Tag.BACK);
        grid.add(backToLogin, 0, 2);
        backToLogin.setDisable(true);

        if (timeout == false) {

          
            Timeline animation = new Timeline(
                    new KeyFrame(Duration.seconds(10), (ActionEvent actionEvent) -> {
                        backToLogin.setDisable(false);
                    }));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
            timeout = true;

        }

        backToLogin.setOnAction((ActionEvent t) -> {
            backToLogin.setDisable(false);
            displayView(Tag.DISPLAY_DEFAULT);

        });
    }

    //AC-08 System Use Notification
    private void systemUserNotice() {

        Text useNotice = new Text("System use Notification\n "
                + "(i) that the user is accessing a U.S. Government information system; \n"
                + "(ii) that system usage may be monitored, recorded, and subject to audit;\n"
                + "(iii) that unauthorized use of the system is prohibited and subject to criminal "
                + "and civil penalties; and \n"
                + "(iv) that use of the system indicates consent to monitoring and recording. ");

        accept = new Button("ACCEPT");
        cancel = new Button("CANCEL");

        grid.add(useNotice, 0, 0, 2, 1);
        grid.add(accept, 0, 2);
        grid.add(cancel, 1, 2);

        accept.setOnAction((ActionEvent t) -> {

            registerTransaction("accepted system notification");
            displayView("multiFactAuthView");

        });

        cancel.setOnAction((ActionEvent t) -> {

            registerTransaction("declined system transaction");
            displayView(Tag.DISPLAY_DEFAULT);

        });

    }

    //IA-2(1) IDENTIFICATION AND AUTHENTICATION (ORGANIZATIONAL USERS) | NETWORK ACCESS TO PRIVILEGED ACCOUNTS
    private void multiFactAuthView() {

        Text multiFactNotice = new Text("Please Enter telephone number.\n"
                + "Please do not include special Characters like - or ( ).\n"
                + "US Phone Number please enter 10 Digits ");

        //Create TextField
        phone = new TextField();
        authCode = new TextField();

        //Create Labels
        phoneLabel = new Label(Tag.PHONE);
        authCodeLabel = new Label(Tag.MFA);

        //Create Buttons
        sendPhoneMessage = new Button(Tag.SUBMIT);
        cancelPhoneMessage = new Button(Tag.CANCEL);
        submitMFACode = new Button(Tag.SUBMIT);

        //Add Components
        grid.add(multiFactNotice, 0, 0, 2, 1);
        grid.add(phoneLabel, 0, 2);
        grid.add(phone, 1, 2);
        grid.add(authCodeLabel, 0, 3);
        grid.add(authCode, 1, 3);
        grid.add(sendPhoneMessage, 2, 2);
        grid.add(cancelPhoneMessage, 3, 2);
        grid.add(submitMFACode, 2, 3);

        sendPhoneMessage.setOnAction((ActionEvent t) -> {

            try {

                //generate random 5 digit number 
                authCodeNum = String.valueOf(gen());

                //Send to authCodeNum to phone number provided
                MFATextClass mfa = new MFATextClass(phone.getText(), authCodeNum);

                displayView("multiFactAuthView");
                actiontargetDisplay("Sucess");

                registerTransaction("MFA Text Sucessfully Sent");

            } catch (TwilioRestException ex) {

                Logger.getLogger(SDEV425HW2.class.getName()).log(Level.SEVERE, null, ex);

                displayView("multiFactAuthView");
                actiontargetDisplay("Phone");

                registerTransaction("MFA text Exception");
            }
        });

        cancelPhoneMessage.setOnAction((ActionEvent t) -> {

            registerTransaction("declined MFA");
            displayView(Tag.DISPLAY_DEFAULT);

        });

        submitMFACode.setOnAction((ActionEvent t) -> {

            registerTransaction("MFA Checked");
            displayView("LogIn");

        });

    }

    //AU-3 - CONTENT OF AUDIT RECORDS
    private void registerTransaction(String transType) {

        /* Create EntityManagerFactory */
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("TRANSACTIONLOG");

        /* Create EntityManager */
        em = factory.createEntityManager();

        //Log Transaction 
        trans = new TransactionLog();

        //Controller Action
        action = new LogController(factory);
        trans.setDateCreated(Date.from(Instant.now()));
        trans.setUsername(userTextField.getText());
        trans.setTransactionType(transType);

        try {
            action.create(trans);

        } catch (Exception ex) {
            Logger.getLogger(SDEV425HW2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //AU-9(6) - PROTECTION OF AUDIT INFORMATION | READ ONLY ACCESS
    private void auditLog() {

        registerTransaction("Audit Log Request");
        ZonedDateTime now = ZonedDateTime.now();
        String filename = gen() + "-AuditLog-" + now.toLocalDate() + ".xlsx";

        //create a new workbook
        Workbook wb = new XSSFWorkbook();

        //Counter
        int counter = 0;

        //Add a sheet
        Sheet sheet = wb.createSheet("Audit Log");

        //Add Column Titles
        Row titles = sheet.createRow(0);
        Cell userNameCol = titles.createCell(0);
        Cell transTypeCol = titles.createCell(1);
        Cell dateTime = titles.createCell(2);
        userNameCol.setCellValue("User Name");
        transTypeCol.setCellValue("Transaction Type");
        dateTime.setCellValue("Time");

        EntityManagerFactory logFactory = Persistence.createEntityManagerFactory("TRANSACTIONLOG");

        LogController jpa = new LogController(logFactory);

        List<TransactionLog> list = jpa.findTransactionLogEntities();

        Map<Integer, TransactionLog> logDatatoExcel = new HashMap<>();

        for (TransactionLog t : list) {

            counter++;

            logDatatoExcel.put(counter, t);

            Row row = sheet.createRow(counter);
            Cell cell1 = row.createCell(0);
            Cell cell2 = row.createCell(1);
            Cell cell3 = row.createCell(2);

            cell1.setCellValue(logDatatoExcel.get(counter).getUsername());
            cell2.setCellValue(logDatatoExcel.get(counter).getTransactionType());
            cell3.setCellValue(logDatatoExcel.get(counter).getDateCreated().toString());

        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        sheet.protectSheet("s3cr3t");

        try {

            try (FileOutputStream fileOut = new FileOutputStream(filename)) {

                wb.write(fileOut);

            }

        } catch (IOException e) {

        }

        logDatatoExcel.clear();
        actiontargetDisplay("Sucess");
        registerTransaction("Audit Log Sucessfully Sent");

    }

//Random 5 digit generator 
    public static int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        // System.out.println("authCodeNum");
        launch(args);
    }
}
