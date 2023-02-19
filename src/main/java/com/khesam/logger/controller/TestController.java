package com.khesam.logger.controller;


import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogMe;
import com.khesam.logger.controller.dto.TestHappyRequestData;
import com.khesam.logger.controller.dto.TestRequestData;
import com.khesam.logger.service.TestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@EnableLogging(value = EnableLogging.Layer.CONTROLLER, profile = "test")
@ApplicationScoped
public class TestController implements TestRestResource {

    private final TestService testService;

    @Inject
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Override
    @LogMe(incomeMessage = "Enter test controller", outcomeMessage = "Exit test controller")
    public Response happy(TestHappyRequestData testHappyRequestData) {
        return Response.ok(callee(testHappyRequestData)).build();
    }

    @Override
    @LogMe
    public Response catchException(TestRequestData request) {
        return Response.ok(
                testService.getSafe(request)
        ).build();
    }

    @Override
    @LogMe
    public Response uncatchException(TestRequestData request) {
        return Response.ok(
                testService.getUnsafe(request)
        ).build();
    }

    @Override
    @LogMe
    public Response rethrowException(TestRequestData request) {
        return Response.ok(
                testService.rethrowGetUnsafe(request)
        ).build();
    }

    @Override
    @LogMe
    public Response successfulExternalCall() {
        return Response.ok(
                testService.successfulExternalCall()
        ).build();
    }

    @Override
    @LogMe
    public Response unsuccessfulExternalCall() {
        testService.unsuccessfulExternalCall();
        return Response.noContent().build();
    }

    public String callee(TestHappyRequestData testHappyRequestData) {
        return privateCallee(testHappyRequestData);
    }

    private String privateCallee(TestHappyRequestData testHappyRequestData) {
        return "Hello " + testHappyRequestData.getFirstname() + " " + testHappyRequestData.getLastname();
    }
}
