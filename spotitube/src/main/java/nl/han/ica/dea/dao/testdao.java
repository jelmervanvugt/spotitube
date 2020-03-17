package nl.han.ica.dea.dao;

import com.sun.security.jgss.GSSUtil;
import nl.han.ica.dea.database.util.DatabaseProperties;

import java.sql.*;

public class testdao {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();

    public testdao() {
        setConnection();
    }

    private void setConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

    public void getAll() {
        ResultSet resultSet = null;
        try {

            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("select * from test");
            
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e);
        }

    }

}
