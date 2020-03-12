package nl.han.ica.dea;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloWorld {

    @Path("helloworld")
    @GET
    public String hellowWorld() {
        return "hello world";
    }



}
