package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.dto.LoginDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private LoginResponseDataMapper loginResponseDataMapper;

    public Response checkCredentials(LoginDTO loginDTO) {
        Response response = null;
        initConnection();
        try {
            if (doesUserExist(loginDTO)) {
                generateToken(loginDTO);
                return response = Response
                        .status(Response.Status.OK)
                        .entity(loginResponseDataMapper.mapToDTO(getUser(loginDTO)))
                        .build();
            } else {
                response = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        closeConnection();
        return response;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getUser(LoginDTO loginDTO) throws SQLException {
        var sql = "call getUser(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        return stmt.executeQuery();
    }

    private void initConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

    private boolean doesUserExist(LoginDTO loginDTO) throws SQLException, NoSuchAlgorithmException {
        var sql = "call doesUserExist(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        stmt.setString(2, stringToHash(loginDTO.getPassword()));
        return procesResults(stmt.executeQuery()) == 1;
    }

    private int procesResults(ResultSet rs) throws SQLException {
        int nResults = 0;
        while (rs.next()) {
            nResults = rs.getInt("nUsers");
        }
        return nResults;
    }

    private void generateToken(LoginDTO loginDTO) throws SQLException {
        var sql = "call generateToken(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        stmt.executeUpdate();
    }

    private String stringToHash(String string) throws NoSuchAlgorithmException {
        return DigestUtils.sha256Hex(string);
    }

    @Inject
    private void setLoginResponseDataMapper(LoginResponseDataMapper loginResponseDataMapper) {
        this.loginResponseDataMapper = loginResponseDataMapper;
    }

}
