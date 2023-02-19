package com.khesam.logger.service;


import com.khesam.logger.common.client.DummyClient;
import com.khesam.logger.common.client.dto.SuccessResponseData;
import com.khesam.logger.common.exception.TestException;
import com.khesam.logger.common.exception.TestResponseStatus;
import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogMe;
import com.khesam.logger.controller.dto.TestRequestData;
import com.khesam.logger.controller.dto.TestResponseData;
import com.khesam.logger.repository.TestRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@EnableLogging(EnableLogging.Layer.SERVICE)
@ApplicationScoped
public class TestService {

    private final TestRepository testRepository;
    private final DummyClient dummyClient;

    @Inject
    public TestService(TestRepository testRepository, @RestClient DummyClient dummyClient) {
        this.testRepository = testRepository;
        this.dummyClient = dummyClient;
    }

    @LogMe
    public TestResponseData getSafe(TestRequestData input) {
        return new TestResponseData(
                testRepository.safeFind(input.getId())
        );
    }

    @LogMe
    public TestResponseData getUnsafe(TestRequestData input) {
        return new TestResponseData(
                testRepository.unsafeFind(input.getId())
        );
    }

    @LogMe
    public TestResponseData rethrowGetUnsafe(TestRequestData input) {
        try {
            return new TestResponseData(
                    testRepository.unsafeFind(input.getId())
            );
        } catch (Exception ex) {
            throw new TestException(TestResponseStatus.BAD_REQUEST);
        }
    }

    @LogMe
    public SuccessResponseData successfulExternalCall() {
        return dummyClient.successfulCall();
    }

    @LogMe
    public void unsuccessfulExternalCall() {
        dummyClient.unsuccessfulCall();
    }
}
