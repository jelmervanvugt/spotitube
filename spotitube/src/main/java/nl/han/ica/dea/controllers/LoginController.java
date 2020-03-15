package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dto.LoginDTO;
import nl.han.ica.dea.dto.LoginResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        if(checkCredentials(loginDTO)) {

            return Response
                    .status(Response.Status.OK)
                    .entity(new LoginResponseDTO(loginDTO.getUser(), "1234-1234-1234"))
                    .build();
        }
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

    private boolean checkCredentials(LoginDTO loginDTO) {
        return loginDTO.getUser().equals("jelmo") && loginDTO.getPassword().equals("jelmo");
    }

}
