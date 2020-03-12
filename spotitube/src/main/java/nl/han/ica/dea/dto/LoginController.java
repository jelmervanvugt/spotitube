package nl.han.ica.dea.dto;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("login")
public class LoginController {

    @Post
    @Consumes(MediaType.APPLICATION_JSON)

    public void printCredentials() {

    }

}
