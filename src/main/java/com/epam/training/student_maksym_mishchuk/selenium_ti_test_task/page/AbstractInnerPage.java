package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractInnerPage extends AbstractPage{
    public AbstractInnerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        throw new UnsupportedOperationException("You cannot open an inner page");
    }
}
