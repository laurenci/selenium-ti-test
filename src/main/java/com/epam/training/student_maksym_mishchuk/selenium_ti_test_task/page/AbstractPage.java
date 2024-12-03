package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
 protected WebDriver driver;
 public AbstractPage(WebDriver driver) {
  this.driver = driver;
 }
 protected abstract AbstractPage openPage();
}
