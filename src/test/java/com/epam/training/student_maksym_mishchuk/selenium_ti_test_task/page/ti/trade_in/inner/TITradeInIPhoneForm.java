package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class TITradeInIPhoneForm extends AbstractInnerPage {
    protected WebElement form;

    protected WebElement modelDropdown;
    protected WebElement memoryDropdown;
    protected WebElement colorDropdown;

    private final String formPattern = "//div[contains(@class, 'trade-in-holder %s active')]";
    private final String dropdownMenuPattern = "/descendant::label[contains(text(), '%s')]/ancestor::div[contains(@class, 'select-input-holder')]";

    private final String dropdownMenuItemPatternStrict = "/div[@class='select-input-dropdown']/div[text()='%s']";
    private final String dropdownMenuItemPatternSoft = "/div[@class='select-input-dropdown']/div[contains(text(), '%s')]";

    private final Map<String, String> xPaths = new HashMap<>();

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    public TITradeInIPhoneForm(WebDriver driver, String formName) {
        super(driver);
        initXPath("form", formPattern, formName);
        this.form = initWebElement("form");

        initXPath("model", xPaths.get("form").concat(dropdownMenuPattern), "Модель");
        this.modelDropdown = initWebElement("model");

        initXPath("memory", xPaths.get("form").concat(dropdownMenuPattern), "Вбудована");
        this.memoryDropdown = initWebElement("memory");

        initXPath("color", xPaths.get("form").concat(dropdownMenuPattern), "Колір");
        this.colorDropdown = initWebElement("color");
    }

    private void initXPath(String key, String xPathPattern, String... values) {
        xPaths.put(key, xPathPattern.formatted(values));
    }

    private WebElement initWebElement(String xPathKey) {
        return driver.findElement(By.xpath(xPaths.get(xPathKey)));
    }

    public TITradeInIPhoneForm setModel(String model) {
        setDropdownValue(modelDropdown, "model", model, true);
        return this;
    }

    public TITradeInIPhoneForm setMemory(String memory) {
        setDropdownValue(memoryDropdown, "memory", memory, true);
        return this;
    }

    public TITradeInIPhoneForm setColor(String color) {
        setDropdownValue(colorDropdown, "color", color, false);
        return this;
    }

    private void setDropdownValue(WebElement dropdown, String key, String value, boolean isStrict) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

        if (isStrict)
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
                    xPaths.get(key).concat(dropdownMenuItemPatternStrict.formatted(value))
            )))).click();
        else
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
                    xPaths.get(key).concat(dropdownMenuItemPatternSoft.formatted(value))
            )))).click();
    }
}
