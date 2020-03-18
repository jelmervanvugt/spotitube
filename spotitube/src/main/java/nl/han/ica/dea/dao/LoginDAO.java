package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;

import java.sql.*;

public class LoginDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public LoginDAO() {
        initConnection();
    }

    public boolean checkCredentials(String user, String password) {
        try {
            queryDb(user, password);
            if (procesResults() != 1) {
                return false;
            }
            generateToken(user, password);
        } catch (SQLException e) {
            System.out.println("Foutmelding in LoginDAO");
        }
        return true;
    }

    public String getToken(String user, String password) {
        String token = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("call getUser(\"" + user + "\", \"" + password + "\");");

            while (rs.next()) {
               token = rs.getString("token");
            }
        } catch(SQLException e) {
            System.out.println("Foutmelding in LoginDAO");
        }
        return token;
    }

    public String getFullName(String user, String password) {
        String fullName = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("call getUser(\"" + user + "\", \"" + password + "\");");

            while (rs.next()) {
                fullName = rs.getString("fullname");
            }
        } catch(SQLException e) {
            System.out.println("Foutmelding in LoginDAO");
        }
        return fullName;
    }

    private void initConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

    private void queryDb(String user, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call doesUserExist(\"" + user + "\", \"" + password + "\");");
    }

    private int procesResults() throws SQLException {
        int nResults = 0;
        while (rs.next()) {
            nResults = rs.getInt("nUsers");
        }
        return nResults;
    }

    private void generateToken(String user, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call generateToken(\"" + user + "\", \"" + password + "\");");
    }

}
