package com.khesam.logger.repository;


import com.khesam.logger.common.exception.TestException;
import com.khesam.logger.common.exception.TestResponseStatus;
import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogMe;

import javax.enterprise.context.ApplicationScoped;


@EnableLogging(EnableLogging.Layer.REPOSITORY)
@ApplicationScoped
public class TestRepository {

    @LogMe
    public String safeFind(String input) {
        int i;
        try {
            i = Integer.parseInt(input);
        } catch (Exception ex) {
            throw new TestException(ex, TestResponseStatus.BAD_REQUEST);
        }

        return select(i);
    }

    @LogMe
    public String unsafeFind(String input) {
        return select(
                Integer.parseInt(input)
        );
    }

    @LogMe
    private String select(int input) {
        if (input < 0) {
            throw new TestException(TestResponseStatus.USER_NOT_FOUND);
        }
        return "Asghar Davaloo";
    }
}
