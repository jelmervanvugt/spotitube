package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.service.ConnectionService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginDAO {

    private LoginResponseDataMapper loginResponseDataMapper;
    private ConnectionService connectionService;

    public LoginResponseDTO checkCredentials(LoginDTO loginDTO) throws SQLException {
        LoginResponseDTO loginResponseDTO;
        connectionService.initConnection();
        loginResponseDTO = loginResponseDataMapper.mapToDTO(getUser(loginDTO));
        connectionService.closeConnection();
        return loginResponseDTO;
    }

    public boolean doesUserExist(LoginDTO loginDTO) throws SQLException, NoSuchAlgorithmException {
        connectionService.initConnection();
        var sql = "call doesUserExist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        stmt.setString(2, stringToHash(loginDTO.getPassword()));
        boolean doesExist = procesResults(stmt.executeQuery());
        connectionService.closeConnection();
        return doesExist;
    }

    public void generateToken(LoginDTO loginDTO) throws SQLException {
        connectionService.initConnection();
        var sql = "call generateToken(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    private String stringToHash(String string) throws NoSuchAlgorithmException {
        return DigestUtils.sha256Hex(string);
    }

    private ResultSet getUser(LoginDTO loginDTO) throws SQLException {
        var sql = "call getUser(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, loginDTO.getUser());
        return stmt.executeQuery();
    }

    private boolean procesResults(ResultSet rs) throws SQLException {
        int nResults = 0;
        while (rs.next()) {
            nResults = rs.getInt("nUsers");
        }
        return nResults == 1;
    }

    @Inject
    public void setLoginResponseDataMapper(LoginResponseDataMapper loginResponseDataMapper) {
        this.loginResponseDataMapper = loginResponseDataMapper;
    }

    @Inject
    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
}
