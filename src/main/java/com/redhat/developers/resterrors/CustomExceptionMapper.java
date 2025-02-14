package com.redhat.developers.resterrors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<InfoNotFoundException> {

    @Override
    public Response toResponse(InfoNotFoundException e) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // pretty-print

        ClientErrors errorResponse = new ClientErrors(401, e.getMessage());
        System.out.println(">>>> " + errorResponse.toString());
        try {
            return Response.status(Response.Status.UNAUTHORIZED).entity(mapper.writeValueAsString(errorResponse)).build();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
