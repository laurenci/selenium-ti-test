package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TITradeInNewDeviceForm;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TITradeInResultPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.trade_in.inner.TiTradeInMyDeviceForm;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.VisibleAjaxElementLocatorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.function.BiPredicate;

public class TITradeInPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'trade-in-holder change')]/descendant::li[contains(@class, 'change-category__item')]")
    private List<WebElement> changeCategoryItems;

    @FindBy(xpath = "//div[contains(@class, 'trade-in-holder buy')]/descendant::li[contains(@class, 'change-category__item')]")
    private List<WebElement> buyCategoryItems;

    private final BiPredicate<WebElement, String> strictPredicate = (element, value) -> element.getText().trim().equals(value);
    private final BiPredicate<WebElement, String> softPredicate = (element, value) -> element.getText().trim().contains(value);

    public TITradeInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new VisibleAjaxElementLocatorFactory(driver, 5), this);
    }

    @Override
    public TITradeInPage openPage() {
        driver.get("https://ti.ua/ua/trade-in/");
        return this;
    }

    public TITradeInPage setMyIPhone(iPhone iPhone) {
        setChangeCategoryItem(changeCategoryItems,"iPhone Б/У");

        new TiTradeInMyDeviceForm(driver)
                .setValue("Модель", iPhone.model(), strictPredicate)
                .setValue("Вбудована пам'ять", iPhone.memory(), strictPredicate)
                .setValue("Колір", iPhone.color(), softPredicate);
        return this;
    }

    public TITradeInPage setNewIPhone(iPhone iPhone) {
        setChangeCategoryItem(buyCategoryItems, "Смартфони iPhone");
        new TITradeInNewDeviceForm(driver)
                .setValue("Модель", iPhone.model(), strictPredicate)
                .setValue("Вбудована пам'ять", iPhone.memory(), strictPredicate)
                .setValue("Колір", iPhone.color(), softPredicate);
        return this;
    }

    public TITradeInResultPage getResults() {
        return new TITradeInResultPage(driver);
    }

    private void setChangeCategoryItem(List<WebElement> categoryItems, String categoryName) {
        categoryItems.stream()
                .filter(element -> element.getText().equals(categoryName))
                .findFirst().ifPresent(WebElement::click);
    }
}