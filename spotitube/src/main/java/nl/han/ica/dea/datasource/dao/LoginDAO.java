package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.datasource.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.datasource.exceptions.InvalidCredentialsException;
import nl.han.ica.dea.service.ConnectionService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginDAO {

    private LoginResponseDataMapper loginResponseDataMapper;
    private ConnectionService connectionService;

    public Response checkCredentials(LoginDTO loginDTO) {
        Response response = null;
        connectionService.initConnection();
        try {
            if (doesUserExist(loginDTO)) {
                generateToken(loginDTO);
                 response = Response
                        .status(Response.Status.OK)
                        .entity(loginResponseDataMapper.mapToDTO(getUser(loginDTO)))
                        .build();
            } else {
                throw new InvalidCredentialsException();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    private ResultSet getUser(LoginDTO loginDTO) throws SQLException {
        var sql = "call getUser(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        return stmt.executeQuery();
    }

    private boolean doesUserExist(LoginDTO loginDTO) throws SQLException, NoSuchAlgorithmException {
        var sql = "call doesUserExist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
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
        var stmt = connectionService.getConnection().prepareStatement(sql);
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

    @Inject
    private void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
}
