package com.khesam.logger.service;


import com.khesam.logger.common.exception.TestException;
import com.khesam.logger.common.exception.TestResponseStatus;
import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogMe;
import com.khesam.logger.controller.dto.TestRequestData;
import com.khesam.logger.controller.dto.TestResponseData;
import com.khesam.logger.repository.TestRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@EnableLogging(EnableLogging.Layer.SERVICE)
@ApplicationScoped
public class TestService {

    private final TestRepository testRepository;

    @Inject
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
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
}
