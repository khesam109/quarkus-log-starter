package com.khesam.logger.common.client;

import com.khesam.logger.common.client.dto.SuccessResponseData;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dummy")
@RequestScoped
@RegisterRestClient(baseUri = "http://localhost:9093")
public interface DummyClient {

    @GET
    @Path("/successful")
    @Produces(MediaType.APPLICATION_JSON)
    SuccessResponseData successfulCall();

    /**
     * this dummy service should always 5xx response with an unknown body
     * we want to log this unknown body
     */
    @GET
    @Path("/unsuccessful")
    @Produces(MediaType.APPLICATION_JSON)
    Void unsuccessfulCall();
}
