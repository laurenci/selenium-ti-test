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

public class TITradeInPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'trade-in-holder change')]/descendant::li[contains(@class, 'change-category__item')]")
    private List<WebElement> changeCategoryItems;

    @FindBy(xpath = "//div[contains(@class, 'trade-in-holder buy')]/descendant::li[contains(@class, 'change-category__item')]")
    private List<WebElement> buyCategoryItems;

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
                .setValueStrict("Модель", iPhone.model())
                .setValueStrict("Вбудована пам'ять", iPhone.memory())
                .setValueSoft("Колір", iPhone.color());
        return this;
    }

    public TITradeInPage setNewIPhone(iPhone iPhone) {
        setChangeCategoryItem(buyCategoryItems, "Смартфони iPhone");

        new TITradeInNewDeviceForm(driver)
                .setValueStrict("Модель", iPhone.model())
                .setValueStrict("Вбудована пам'ять", iPhone.memory())
                .setValueSoft("Колір", iPhone.color());
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
