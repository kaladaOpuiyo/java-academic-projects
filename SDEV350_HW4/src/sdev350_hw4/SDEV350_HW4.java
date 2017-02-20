/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev350_hw4;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Kalada Opuiyo
 */
public class SDEV350_HW4 {

    public static void main(String[] args) throws SQLException {
        boolean run = true;
        Scanner input = new Scanner(System.in);

        System.out.print("The Application allows the user to preform CRUD functions on the Category table\n");
        while (run) {

            System.out.print("\nPlease Select from the Options below\n"
                    + "(1)CREATE\n"
                    + "(2)LIST\n"
                    + "(3)UPDATE\n"
                    + "(4)DELETE\n"
                    + "(5)EXIT\n");

            System.out.print("Please enter Value: ");

            Crud crud = new Crud(input.next());

        }
    }

    private static class Crud {

        Scanner input = new Scanner(System.in);

        public Crud(String selection) throws SQLException {

            executeRequest(selection);
        }

        private Connection connectionToDatabase() throws SQLException {

            Connection connect;

            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@//localhost:1522/orcl1");
            ods.setUser("Homework_1");
            ods.setPassword("kalada");
            connect = ods.getConnection();

            return connect;
        }

        private void deleteCategory() throws SQLException {
            String id;
            PreparedStatement ps_select;
            PreparedStatement ps_delete = null;

            try {

                String delete = "DELETE FROM CATEGORY WHERE ID=(?)";
                String display = "SELECT ID FROM HOMEWORK_1.CATEGORY WHERE ID=? ";

                ps_select = connectionToDatabase().prepareStatement(display);

                System.out.print("Please enter the ID for the Category you would like to delete:");

                boolean run = true;

                while (run) {

                    id = input.next();
                    String stored_id = null;
                    ps_select.setString(1, id);
                    ResultSet rs = ps_select.executeQuery();

                    while (rs.next()) {

                        stored_id = rs.getString(1);
                    }

                    if (stored_id == null ? id == null : stored_id.equals(id)) {

                        ps_delete = connectionToDatabase().prepareStatement(delete);

                        ps_delete.setString(1, id);

                        int rowsDeleted = ps_delete.executeUpdate();

                        if (rowsDeleted > 0) {

                            System.out.print("You have deleted Category ID " + id + "\n");
                            run = false;
                        } else {
                            System.out.print("TryAgain");
                            exit(0);
                        }

                    } else {

                        System.out.print("ID enter is incorrect, please try again:");

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SDEV350_HW4.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps_delete != null) {
                    ps_delete.close();
                }

                if (connectionToDatabase() != null) {
                    connectionToDatabase().close();
                }

            }

        }

        private void updateCategory() throws SQLException {

            String id;
            PreparedStatement ps_select;
            PreparedStatement ps_update = null;
            String categoryName;

            try {

                String update;
                update = "UPDATE CATEGORY SET NAME = (?),DATE_CREATED = CURRENT_TIMESTAMP " + "WHERE ID = (?)";
                String display = "SELECT ID FROM HOMEWORK_1.CATEGORY WHERE ID=? ";

                ps_select = connectionToDatabase().prepareStatement(display);

                System.out.print("Please enter the ID for the Category you would like to update:");

                boolean run = true;

                while (run) {

                    id = input.next();
                    String stored_id = null;
                    ps_select.setString(1, id);
                    ResultSet rs = ps_select.executeQuery();

                    while (rs.next()) {

                        stored_id = rs.getString(1);

                    }

                    if (stored_id == null ? id == null : stored_id.equals(id)) {

                        ps_update = connectionToDatabase().prepareStatement(update);

                        System.out.print("Enter the New Category Name:");
                        categoryName = input.next();
                        System.out.print(categoryName);
                        ps_update.setString(2, id);

                        ps_update.setString(1, categoryName);

                        int rowsUpdated = ps_update.executeUpdate();

                        if (rowsUpdated > 0) {

                            System.out.print("You have changed Category ID " + id + " to " + categoryName + "\n");
                            run = false;
                        } else {
                            System.out.print("TryAgain");
                            exit(0);
                        }

                    } else {

                        System.out.print("ID enter is incorrect, please try again:");

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SDEV350_HW4.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps_update != null) {
                    ps_update.close();
                }

                if (connectionToDatabase() != null) {
                    connectionToDatabase().close();
                }

            }
        }

        private void listCategory() throws SQLException {

            String selection = null;
            Boolean run = true;

            while (run) {

                System.out.print("Enter (ALL) to list all Categories or \n"
                        + "Enter an (ID) or (Name) to list specfic Category\n"
                        + "Please enter value here:");
                selection = input.next();

                Boolean all = "ALL".equals(selection) || "all".equals(selection) || "All".equals(selection);
                Boolean isNumber = selection.chars().allMatch(Character::isDigit);
                Boolean isAlphbet = selection.chars().allMatch(Character::isAlphabetic);

                int type = all ? 1 : isNumber ? 2 : isAlphbet ? 3 : 0;

                switch (type) {

                    case 1:
                        run = selectALL();
                        break;
                    case 2:
                        run = selectCategoryByID(selection);
                        break;
                    case 3:
                        run = selectCategoryByName(selection);
                        break;
                   
                    default:
                        exit(0);
                        break;

                }
            }
        }

        private Boolean selectALL() throws SQLException {

            PreparedStatement ps_select;
            String selectAll = "SELECT * FROM CATEGORY";

            ps_select = connectionToDatabase().prepareStatement(selectAll);

            ResultSet rs = null;

            rs = ps_select.executeQuery();

            while (rs.next()) {

                System.out.print("Category ID: " + rs.getString(1) + " Category NAME: " + rs.getString(2) + "\n");

            }

            return false;
        }

        private Boolean selectCategoryByID(String selection) throws SQLException {

            PreparedStatement ps_select;
            String selectCategory = "SELECT NAME FROM CATEGORY where ID = ?";

            Boolean run = true;

            ps_select = connectionToDatabase().prepareStatement(selectCategory);

            ps_select.setString(1, selection);

            ResultSet rs = ps_select.executeQuery();

            if (rs.next() == false) {

                System.out.print("Invalid Entry\n");

            } else {

                System.out.print("\n Category Name: " + rs.getString(1) + "\n");

                run = false;
            }

            return run;

        }

        private Boolean selectCategoryByName(String selection) throws SQLException {

            PreparedStatement ps_select;
            String selectCategory = "SELECT ID FROM CATEGORY where NAME = ?";
            Boolean run = true;

            ps_select = connectionToDatabase().prepareStatement(selectCategory);

            ps_select.setString(1, selection);

            ResultSet rs = ps_select.executeQuery();

            if (rs.next() == false) {

                System.out.print("Invalid Entry\n");

            } else {

                System.out.print("Category ID: " + rs.getString(1) + "\n");

                run = false;
            }
            return run;

        }

        private void createCustomer() throws SQLException {

            String id;
            PreparedStatement ps_select;
            PreparedStatement ps_create = null;
            String categoryName;

            try {

                String insert = "INSERT INTO CATEGORY(ID,NAME,DATE_CREATED)VALUES(?,?,CURRENT_TIMESTAMP)";
                String display = "SELECT ID FROM HOMEWORK_1.CATEGORY WHERE ID=? ";

                ps_select = connectionToDatabase().prepareStatement(display);

                System.out.print("Please enter the ID for the Category you would like to create:");

                boolean run = true;

                while (run) {

                    id = input.next();
                    String stored_id = null;
                    ps_select.setString(1, id);
                    ResultSet rs = ps_select.executeQuery();

                    while (rs.next()) {

                        stored_id = rs.getString(1);

                    }

                    if (stored_id == null ? id == null : stored_id.equals(id)) {

                        System.out.print("ID already in system, please try again:");

                    } else {
                        ps_create = connectionToDatabase().prepareStatement(insert);

                        System.out.print("Enter the New Category Name:");
                        categoryName = input.next();

                        ps_create.setString(1, id);

                        ps_create.setString(2, categoryName);

                        int rowsUpdated = ps_create.executeUpdate();

                       

                        if (rowsUpdated > 0) {

                            System.out.print("You have created new Category: ID " + id + ", Category Name: " + categoryName + "\n");
                            run = false;
                        } else {
                            System.out.print("TryAgain");
                            exit(0);

                        }

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SDEV350_HW4.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps_create != null) {
                    ps_create.close();
                }

                if (connectionToDatabase() != null) {
                    connectionToDatabase().close();
                }

            }
        }

        private boolean executeRequest(String selection) throws SQLException {

            Boolean run = true;

            switch (selection) {

                case ("1"):
                case ("CREATE"):

                    createCustomer();
                    break;

                case ("2"):
                case ("LIST ALL"):

                    listCategory();
                    break;

                case ("3"):
                case ("UPDATE"):

                    updateCategory();
                    break;

                case ("4"):
                case ("DELETE"):

                    deleteCategory();
                    break;

                default:

                    System.out.print("Good Bye!\n");
                    exit(0);

            }

            return run;
        }

    }

}
