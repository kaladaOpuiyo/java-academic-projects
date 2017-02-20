/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author Kalada Opuiyo
 */
public final class ConnectToDb {

    public static final String QUERYREQUEST_2 = "select user_id from sdev_users  where email = ?";
    public static final String QUERYREQUEST_3 = "select user_id from user_info where user_id = ? and password = ?";
    public static final String QUERYREQUEST_1 = "select user_id,Cardholdername, Cardtype,"
            + "ServiceCode, CardNumber,CAV_CCV2,expiredate,FullTrackData,PIN"
            + " from customeraccount where user_id =?";

    public static ConnectToDb newInstance() {

        return new ConnectToDb();

    }

    private Connection connectionToDatabase() {

        Connection conn = null;
        try {

            ClientDataSource ds = new ClientDataSource();
            ds.setDatabaseName("SDEV425");
            ds.setServerName("localhost");
            ds.setPortNumber(1527);
            ds.setUser("sdev425");
            ds.setPassword("sdev425");
            ds.setDataSourceName("jdbc:derby");
            conn = ds.getConnection();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public ResultSet newRequest(String... parameters) throws SQLException {

        PreparedStatement stmt = connectionToDatabase().prepareStatement(parameters[0]);

        for (String param : parameters) {

            int index = Arrays.asList(parameters).indexOf(param);

            Boolean indexCheck = index != 0;

            if (indexCheck) {
                stmt.setString(index, param);
            }

        }
        return stmt.executeQuery();
    }

}
