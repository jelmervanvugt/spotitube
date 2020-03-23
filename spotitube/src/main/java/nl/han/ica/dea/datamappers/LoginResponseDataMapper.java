package nl.han.ica.dea.datamappers;

import nl.han.ica.dea.dto.LoginResponseDTO;

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
