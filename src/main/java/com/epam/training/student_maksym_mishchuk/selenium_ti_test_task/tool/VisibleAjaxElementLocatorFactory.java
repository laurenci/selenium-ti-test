package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool;

import lombok.AllArgsConstructor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

@AllArgsConstructor
public class VisibleAjaxElementLocatorFactory implements ElementLocatorFactory {
    private WebDriver driver;
    private int timeOutInSeconds;

    @Override
    public ElementLocator createLocator(Field field) {
        return new VisibleAjaxElementLocator(driver, field, timeOutInSeconds);
    }

    private static class VisibleAjaxElementLocator extends AjaxElementLocator {
        public VisibleAjaxElementLocator(SearchContext searchContext, Field field, int timeOutInSeconds) {
            super(searchContext, field, timeOutInSeconds);
        }

        @Override
        protected boolean isElementUsable(WebElement element) {
            return element != null && element.isDisplayed() && element.isEnabled();
        }
    }
}
