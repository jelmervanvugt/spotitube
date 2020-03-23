package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.dto.LoginDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.*;

public class LoginDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;
    private LoginResponseDataMapper loginResponseDataMapper;

    public Response checkCredentials(LoginDTO loginDTO) {
        Response response = null;
        try {
            if (doesUserExist(loginDTO)) {
                generateToken(loginDTO);
                getUser(loginDTO);
                return response = Response.status(Response.Status.OK).entity(loginResponseDataMapper.mapToDTO(rs)).build();
            } else {
                response = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("Foutmelding in LoginDAO");
        }
        return response;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getUser(LoginDTO loginDTO) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call getUser(\"" + loginDTO.getUser() + "\", \"" + loginDTO.getPassword() + "\");");
    }

    public void initConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

    private boolean doesUserExist(LoginDTO loginDTO) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call doesUserExist(\"" + loginDTO.getUser() + "\", \"" + loginDTO.getPassword() + "\");");
        return procesResults() == 1;
    }

    private int procesResults() throws SQLException {
        int nResults = 0;
        while (rs.next()) {
            nResults = rs.getInt("nUsers");
        }
        return nResults;
    }

    private void generateToken(LoginDTO loginDTO) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call generateToken(\"" + loginDTO.getUser() + "\", \"" + loginDTO.getPassword() + "\");");
    }

    @Inject
    private void setLoginResponseDataMapper(LoginResponseDataMapper loginResponseDataMapper) {
        this.loginResponseDataMapper = loginResponseDataMapper;
    }

}
