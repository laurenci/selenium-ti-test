package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public abstract class AbstractPage {
 protected WebDriver driver;
 protected abstract AbstractPage openPage();
}
