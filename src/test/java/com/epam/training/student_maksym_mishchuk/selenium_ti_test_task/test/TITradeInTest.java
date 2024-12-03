package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.TITradeInPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.WebDriverProvider;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter.CSVRawDataToIPhoneConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

public class TITradeInTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.getDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trade-in/iPhoneToiPhone.csv")
    void testIPhoneToIPhoneTradeIn(String myIPhoneRawData, String newIphoneRawData) {
        iPhone myIPhone = CSVRawDataToIPhoneConverter.convert(myIPhoneRawData);
        iPhone newIPhone = CSVRawDataToIPhoneConverter.convert(newIphoneRawData);

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
        WebDriverProvider.quitDriver();
    }
}
