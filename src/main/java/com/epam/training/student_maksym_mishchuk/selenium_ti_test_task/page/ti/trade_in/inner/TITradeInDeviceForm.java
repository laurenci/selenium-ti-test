package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class TITradeInDeviceForm extends AbstractInnerPage {
    protected WebElement form;

    private final List<WebElement> selectInputs;

    private final String formPattern = "//div[contains(@class, 'trade-in-holder %s active')]";

    public TITradeInDeviceForm(WebDriver driver, String formName) {
        super(driver);

        this.form = driver.findElement(By.xpath(formPattern.formatted(formName)));
        this.selectInputs = form.findElements(By.cssSelector("div.select-input-holder"));
    }

    public TITradeInDeviceForm setValueStrict(String label, String value) {
        selectInputs.stream()
                .filter(element -> element.getText().trim().equals(label))
                .peek(WebElement::click)
                .flatMap(element -> element.findElements(By.cssSelector("div.select-input-dropdown div")).stream())
                .filter(element -> element.getText().trim().equals(value))
                .forEach(WebElement::click);
        return this;
    }

    public TITradeInDeviceForm setValueSoft(String label, String value) {
        selectInputs.stream()
                .filter(element -> element.getText().trim().equals(label))
                .peek(WebElement::click)
                .flatMap(element -> element.findElements(By.cssSelector("div.select-input-dropdown div")).stream())
                .filter(element -> element.getText().trim().contains(value))
                .forEach(WebElement::click);
        return this;
    }
}
