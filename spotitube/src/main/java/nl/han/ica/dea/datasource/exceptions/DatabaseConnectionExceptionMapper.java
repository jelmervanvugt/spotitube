package nl.han.ica.dea.datasource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
