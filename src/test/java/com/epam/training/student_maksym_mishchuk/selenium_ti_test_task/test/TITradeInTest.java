package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.TITradeInPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.provider.CSVArgumentsProvider;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.watcher.WebDriverExtension;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter.CSVRawDataToIPhoneConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;

@ExtendWith(WebDriverExtension.class)
public class TITradeInTest {
    WebDriver driver;

    public TITradeInTest(WebDriver driver) {
        this.driver = driver;
    }

    @ParameterizedTest
    @ArgumentsSource(CSVArgumentsProvider.class)
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
}
