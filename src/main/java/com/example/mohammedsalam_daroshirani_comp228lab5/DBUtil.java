package com.example.mohammedsalam_daroshirani_comp228lab5;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.sql.*;

public class DBUtil {   // Main DB class

    // 'connection' and 'statement' string initialization
    private static Connection connection = null;
    private static Statement statement = null;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////// CONNECTION CREATION AND DISCONNECTION ESTABLISHMENT (ALWAYS WHEN WORKING WITH JDBC //////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////// INSERT STATEMENT - CREATION FOR GAME AND PLAYER AND GAME_PLAYER DATA ///////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void insertPlayerAndGameData(String tableName, int player_game_id, int game_id, int player_id, String playing_date, int score) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + player_game_id + ", " + game_id + ", " + player_id + ", '" + playing_date + "', " + score + ")";
        statement.executeUpdate(sql);
        System.out.println(player_game_id + ", " + game_id + ", " + player_id + ", '" + playing_date + "' " + score + " is inserted");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    public static void insertPlayerData(String tableName, int player_id, String first_name, String last_name, String address, String postal_code, String province, String phone_number ) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + player_id + ",'" + first_name + "', '" + last_name + "', '" + address + "', '" + postal_code + "', '" + province + "', '" + phone_number + "')";
        statement.executeUpdate(sql);
        System.out.println(player_id + ",'" + first_name + "', '" + last_name + "', '" + address + "', '" + postal_code + "', '" + province + "', '" + phone_number +" is inserted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    public static void insertGameData(String tableName, int game_id, String game_title) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + game_id + ",'" + game_title + "')";
        statement.executeUpdate(sql);
        System.out.println(game_id + " , " + game_title + " is inserted!");
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////// Querying one row's Data for Update /////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ResultSet queryRow(String sql) throws SQLException{
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet(); // allows caching of resultset when connection is ended - has to be available above dbConnect so that it cann be accessed as a result set after the ocnnection is closed

        dbConnect();

        ResultSet rs = null;
        statement.setMaxRows(1);    // limits the result set to 1 row only
        rs = statement.executeQuery(sql);

        crs.populate(rs);   // cached row set caches the one row form resultset
        dbDisconnect();
        return crs;     // return the crs object which is a resultset anyway

    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////// QUERYING DATA From 3 tables individually ///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ResultSet queryPlayerData(String sql) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet(); // collection type of class

        //open db connection
        dbConnect();

        // creating resultSet to capture information from executeQuery which is part of statement class object
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            // cachedRowSet is populated with resultSet rows gotten when above SQL statement is run
            crs.populate(resultSet);

            while (resultSet.next()) {
                // crs column names for respective rows
                int player_id = resultSet.getInt("player_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String postal_code = resultSet.getString("postal_code");
                String province = resultSet.getString("province");
                String phone_number = resultSet.getString("phone_number");
            }

        } catch (SQLException e) {
            System.out.println("Query did not run!");
            System.out.println(e.getMessage());
        }

        // Always run statement.close after the statement is executed
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();

        // crs is returned so that the method can be used to populate resultSET created in the controller method that uses it
        return crs;
    }

    public static ResultSet queryGameData(String sql) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet(); // collection type of class

        //open db connection
        dbConnect();

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            crs.populate(resultSet);

            while (resultSet.next()) {
                int game_id = resultSet.getInt("game_id");
                String game_title = resultSet.getString("game_title");
            }

        } catch (SQLException e) {
            System.out.println("Query did not run!");
            System.out.println(e.getMessage());
        }

        // Always run statement.close after the statement is executed
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
        return crs;
    }

    public static ResultSet queryPlayerAndGameData(String sql) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet(); // collection type of class

        //open db connection
        dbConnect();

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            crs.populate(resultSet);

            while (resultSet.next()) {
                String s_id = resultSet.getString("s_id");
                String s_name = resultSet.getString("s_name");
                System.out.println(s_id + " , " + s_name);
            }

        } catch (SQLException e) {
            System.out.println("Query did not run!");
            System.out.println(e.getMessage());
        }

        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
        return crs;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////// UPDATING DATA FOR EACH TABLE ///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void updatePlayerInformation(String tableName, int player_id, String first_name, String last_name, String address, String postal_code, String province, String phone_number) throws SQLException{
        dbConnect();

        String sql = "UPDATE player SET" +" first_name= '" + first_name + "'," + " last_name= '" + last_name + "'," + " address= '" + address + "'," + " postal_code= '" + postal_code + "'," + " province= '" + province + "'," + " phone_number= '" + phone_number + "'" + " WHERE player_id =" + player_id ;
        statement.executeUpdate(sql);

        if (statement != null) {
            //Close Statement
            statement.close();
        }
        dbDisconnect();
    }

    public static void updateGameInformation(String tableName, int game_id, String game_title) throws SQLException{
        dbConnect();

        String sql = "UPDATE game SET" +" game_id= " + game_id + "," + " game_title= '" + game_title + "' WHERE game_id =" + game_id ;
        statement.executeUpdate(sql);

        if (statement != null) {
            //Close Statement
            statement.close();
        }
        dbDisconnect();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////// DELETE STATEMENT CREATION TO DELETE SPECIFIC ROWS FROM GAME AND PLAYER DATA /////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //public static void deleteGameData(String tableName, int id) throws SQLException{
        //dbConnect();
        //String sql = "DELETE FROM " + tableName + " WHERE s_id ="+ id;
        //statement.executeUpdate(sql);
        //System.out.println("Data is deleted!");
        //if (statement != null) {
            ////Close Statement
            //statement.close();
        //}
        ////Close connection
        //dbDisconnect();
    //}

    //public static void deletePlayerData(String tableName, int id) throws SQLException{
        //dbConnect();
        //String sql = "DELETE FROM " + tableName + " WHERE s_id ="+ id;
        //statement.executeUpdate(sql);
        //System.out.println("Data is deleted!");
        //if (statement != null) {
            ////Close Statement
            //statement.close();
        //}
        //Close connection
        //dbDisconnect();
    //}
///////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////

    // Testing purposes
    public static void main (String[] args) throws SQLException{
        dbConnect();
        dbDisconnect();
    }


}
