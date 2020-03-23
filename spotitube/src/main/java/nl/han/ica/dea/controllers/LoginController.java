package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.LoginDAO;
import nl.han.ica.dea.dto.LoginDTO;
import nl.han.ica.dea.dto.LoginResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    private LoginDAO loginDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        Response response;
        loginDAO.initConnection();
        response = loginDAO.checkCredentials(loginDTO);
        loginDAO.closeConnection();
        return response;
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

}
