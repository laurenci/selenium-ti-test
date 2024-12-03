package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.function.BiPredicate;

public abstract class TITradeInDeviceForm extends AbstractInnerPage {
    private final WebElement form;

    private final Actions actions = new Actions(driver);

    private final List<WebElement> selectInputs;

    private final String formPattern = "//div[contains(@class, 'trade-in-holder %s active')]";

    public TITradeInDeviceForm(WebDriver driver, String formName) {
        super(driver);

        this.form = driver.findElement(By.xpath(formPattern.formatted(formName)));
        this.selectInputs = form.findElements(By.cssSelector("div.select-input-holder"));
    }

    public TITradeInDeviceForm setValue(String label, String value, BiPredicate<WebElement, String> predicate) {
        selectInputs.stream()
                .filter(element -> element.getText().trim().equals(label))
                .peek(element -> actions.scrollToElement(element).perform())
                .peek(WebElement::click)
                .flatMap(element -> element.findElements(By.cssSelector("div.select-input-dropdown div")).stream())
                .filter(element -> predicate.test(element, value))
                .peek(element -> actions.scrollToElement(element).perform())
                .forEach(WebElement::click);
        return this;
    }
}
