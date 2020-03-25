package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.dao.LoginDAO;
import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.datasource.exceptions.InvalidCredentialsException;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginService {

    private LoginDAO loginDAO;

    public Response checkCredentials(LoginDTO loginDTO) {
        try {
            if(loginDAO.doesUserExist(loginDTO)) {
                loginDAO.generateToken(loginDTO);
                return Response
                        .status(Response.Status.OK)
                        .entity(loginDAO.checkCredentials(loginDTO))
                        .build();
            } else {
                throw new InvalidCredentialsException();
            }
        } catch(SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
