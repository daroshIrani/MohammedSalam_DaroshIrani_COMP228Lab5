package com.example.mohammedsalam_daroshirani_comp228lab5;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {   // Main DB class

    // 'connection' and 'statement' string initialization
    private static Connection connection = null;
    private static Statement statement = null;

    ///////////////////////////////////////////// CONNECTION CREATION AND DISCONNECTION ESTABLISHMENT (ALWAYS WHEN WORKING WITH JDBC //////////////////
    public static void dbConnect() throws SQLException{ // Always 'throws exception' whenever you are working with DB's
        // connection to database method
        // String dbURL = "jdbc:oracle:thin:@[host name]:[port]:[SID]"; // Format to follow

        String dbURL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
        String username = "COMP228_F22_sh_20"; // Darosh's username and password
        String password = "password";

        // Making the connection to DB and checking for errors - always remember the try and catch statement
        try {
            connection = DriverManager.getConnection(dbURL, username, password);    // Creates the connection
            System.out.println("Database is connected!");
            statement = connection.createStatement();
        }catch (SQLException e){
            System.out.println("Database is not connected!");
            System.out.println(e.getMessage());
        }
    }

    public static void dbDisconnect() throws SQLException{
        // Closing the connection method

        try{
            if(connection != null && !connection.isClosed()) {  // If we have connection and the connection is not closed
                connection.close();
                System.out.println("Database is disconnected!");
            }

        }catch(Exception e){        // checks all exception when using 'Exception' instead of 'SQL Exceptions' only
            throw e;
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////// INSERT STATEMENT CREATION FOR GAME AND PLAYER DATA ///////////////////////////
    public static void insertGameData(String tableName, int id, String name) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + id + ",'" + name + "')";
        statement.executeUpdate(sql);
        System.out.println(id + " , " + name + " is inserted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    public static void insertPlayerData(String tableName, int id, String name) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + id + ",'" + name + "')";
        statement.executeUpdate(sql);
        System.out.println(id + " , " + name + " is inserted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }
///////////////////////////////////////////////////////

/////////////////////////////////////////////////////// DELETE STATEMENT CREATION TO DELETE SPECIFIC ROWS FROM GAME AND PLAYER DATA /////////////////////
    public static void deleteGameData(String tableName, int id) throws SQLException{
        dbConnect();
        String sql = "DELETE FROM " + tableName + " WHERE s_id ="+ id;
        statement.executeUpdate(sql);
        System.out.println("Data is deleted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    public static void deletePlayerData(String tableName, int id) throws SQLException{
        dbConnect();
        String sql = "DELETE FROM " + tableName + " WHERE s_id ="+ id;
        statement.executeUpdate(sql);
        System.out.println("Data is deleted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }
///////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////

    // Testing purposes
    public static void main (String[] args) throws SQLException{
        dbConnect();
        dbDisconnect();
    }


}
