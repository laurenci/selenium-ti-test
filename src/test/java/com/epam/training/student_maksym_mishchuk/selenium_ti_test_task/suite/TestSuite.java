package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.suite;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.TITradeInTest;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Suite
@SelectClasses({TITradeInTest.class})
public class TestSuite {
    private static final Logger logger = LoggerFactory.getLogger(TestSuite.class);

    @BeforeSuite
    static void before() {
        logger.info("Starting Test Suite");
    }

    @AfterSuite
    static void after() {
        logger.info("Finished Test Suite");
    }

}
