package com.khesam.logger.controller;

import com.khesam.logger.controller.dto.TestHappyRequestData;
import com.khesam.logger.controller.dto.TestRequestData;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public interface TestRestResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/happy")
    Response happy(TestHappyRequestData testHappyRequestData);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/catch-exception")
    Response catchException(TestRequestData testHappyRequestData);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/uncatch-exception")
    Response uncatchException(TestRequestData testHappyRequestData);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rethrow-exception")
    Response rethrowException(TestRequestData testHappyRequestData);


}
