package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.service.LoginService;

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
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

}
