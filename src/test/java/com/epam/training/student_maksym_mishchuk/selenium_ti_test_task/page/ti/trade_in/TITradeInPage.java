package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TITradeInNewIPhoneForm;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TITradeInResultPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TiTradeInMyIPhoneForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TITradeInPage extends AbstractPage {
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private String devicePattern = "//div[contains(@class, '%s')]/descendant::div[@class='trade-in-change-category']/ul/li/a[text()='%s']";

    public TITradeInPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TITradeInPage openPage() {
        driver.get("https://ti.ua/ua/trade-in/");
        return this;
    }

    public TITradeInPage setMyIPhone(iPhone iPhone) {
        wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(devicePattern.formatted("change", "iPhone Б/У"))))
                .click();

        new TiTradeInMyIPhoneForm(driver)
                .setModel(iPhone.model())
                .setMemory(iPhone.memory())
                .setColor(iPhone.color());
        return this;
    }

    public TITradeInPage setNewIPhone(iPhone iPhone) {
        wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(devicePattern.formatted("buy", "Смартфони iPhone"))))
                .click();

        new TITradeInNewIPhoneForm(driver)
                .setModel(iPhone.model())
                .setMemory(iPhone.memory())
                .setColor(iPhone.color());
        return this;
    }

    public TITradeInResultPage getResults() {
        return new TITradeInResultPage(driver);
    }
}
