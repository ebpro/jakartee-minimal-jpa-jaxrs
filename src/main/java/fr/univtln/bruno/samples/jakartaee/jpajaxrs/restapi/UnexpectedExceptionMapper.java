package fr.univtln.bruno.samples.jakartaee.jpajaxrs.restapi;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnexpectedExceptionMapper implements ExceptionMapper<Exception>
{
    @Override
    public Response toResponse(final Exception exception)
    {
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST)
                .entity(exception)
                .type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
}
