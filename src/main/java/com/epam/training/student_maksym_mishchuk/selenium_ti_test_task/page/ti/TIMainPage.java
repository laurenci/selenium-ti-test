package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.catalog.TICatalogMenu;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.search.TISearchBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TIMainPage extends AbstractPage {
    @FindBy(xpath = "//button/span[contains(text(), 'Каталог товарів')]")
    private WebElement catalog;

    @FindBy(xpath = "//input[@enterkeyhint='search']")
    private WebElement search;

    public TIMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public TIMainPage openPage() {
        driver.get("https://ti.ua/");
        return this;
    }

    public TICatalogMenu openCatalogMenu() {
        new Actions(driver).moveToElement(catalog).perform();
        return new TICatalogMenu(driver);
    }

    public TISearchBar search(String text) {
        search.click();
        search.sendKeys(text);
        return new TISearchBar(driver);
    }
}
