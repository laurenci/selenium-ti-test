package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter.CurrencyStringToIntegerConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TITradeInResultPage extends AbstractInnerPage {
    @FindBy(xpath = "//div[@class='change-grade-info']/b[2]")
    private WebElement myDevicePrice;

    @FindBy(className = "trade-in-discount__price")
    private WebElement newDevicePrice;

    @FindBy(className = "trade-in-new__price")
    private WebElement discountedDevicePrice;

    public TITradeInResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Integer getMyDevicePrice() {
        return CurrencyStringToIntegerConverter.convert(myDevicePrice.getText());
    }

    public Integer getNewDevicePrice() {
        return CurrencyStringToIntegerConverter.convert(newDevicePrice.getText());
    }

    public Integer getDiscountedDevicePrice() {
        return CurrencyStringToIntegerConverter.convert(discountedDevicePrice.getText());
    }
}
