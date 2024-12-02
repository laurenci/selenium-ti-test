package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.TITradeInPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class TITradeInTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.getDriver();
    }
    @Test
    void testIPhoneToIPhoneTradeIn() {
        iPhone myIPhone = new iPhone("iPhone 14", "128 ГБ", "Midnight");
        iPhone newIPhone = new iPhone("iPhone 16 Pro Max ", "512 ГБ", "Black Titanium");

        var results = new TITradeInPage(driver)
                .openPage()
                .setMyIPhone(myIPhone)
                .setNewIPhone(newIPhone)
                .getResults();

        Assertions.assertEquals(
                results.getNewDevicePrice() - results.getMyDevicePrice(),
                results.getDiscountedDevicePrice(),
                "Discounted device price is not calculated correctly"
        );
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
