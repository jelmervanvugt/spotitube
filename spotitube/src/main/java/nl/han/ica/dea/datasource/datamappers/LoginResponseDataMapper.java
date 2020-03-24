package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.LoginResponseDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginResponseDataMapper implements DataMapper<LoginResponseDTO> {
    @Override
    public LoginResponseDTO mapToDTO(ResultSet rs) throws SQLException {
        LoginResponseDTO loginResponseDTO = null;
        while (rs.next()) {
            loginResponseDTO = new LoginResponseDTO(rs.getString("fullname"), rs.getString("token"));
        }
        return loginResponseDTO;
    }
}
