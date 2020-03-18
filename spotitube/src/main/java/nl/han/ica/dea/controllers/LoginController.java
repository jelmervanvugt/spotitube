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

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        if(loginDAO.checkCredentials(loginDTO.getUser(), loginDTO.getPassword())) {
            return Response
                    .status(Response.Status.OK)
                    .entity(new LoginResponseDTO(
                            loginDAO.getFullName(loginDTO.getUser(), loginDTO.getPassword()),
                            loginDAO.getToken(loginDTO.getUser(), loginDTO.getPassword())))
                    .build();
        }
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

}
