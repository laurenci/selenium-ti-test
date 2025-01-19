package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.driver;

import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalListener implements WebDriverListener {

    private static final Logger logger = LoggerFactory.getLogger(LocalListener.class);

    @Override
    public void afterClick(WebElement element) {
        logger.info("Clicked on element with tag '{}' and text '{}'.",
                element.getTagName(),
                element.getText());
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("About to navigate to URL: '{}'. Current URL is: '{}'.",
                url,
                driver.getCurrentUrl());
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("Navigation complete! Now on URL: '{}'.",
                driver.getCurrentUrl());
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        logger.info("Shutting down WebDriver session. Current URL is: '{}'.",
                driver.getCurrentUrl());
    }

    @Override
    public void afterQuit(WebDriver driver) {
        logger.info("WebDriver session with URL has been successfully terminated.");
    }
}
