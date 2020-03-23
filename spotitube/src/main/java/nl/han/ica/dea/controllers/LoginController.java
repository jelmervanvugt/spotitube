package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dto.LoginDTO;
import nl.han.ica.dea.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        return loginService.checkCredentials(loginDTO);
    }

    @Inject
    private void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

}
