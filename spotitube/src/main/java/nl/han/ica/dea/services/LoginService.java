package nl.han.ica.dea.services;

import nl.han.ica.dea.dao.LoginDAO;
import nl.han.ica.dea.dto.LoginDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class LoginService {

    private LoginDAO loginDAO;

    public Response checkCredentials(LoginDTO loginDTO)
    { return loginDAO.checkCredentials(loginDTO); }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
