package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.catalog;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIListOfItemsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TICatalogAudioMenu extends AbstractInnerPage {
    @FindBy(xpath = "//a[@href='/ua/akusticheskie-sistemy/']/span[text()='Акустика']")
    private WebElement acoustic;

    public TICatalogAudioMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TIListOfItemsPage openAcousticCatalog() {
        acoustic.click();
        return new TIListOfItemsPage(driver);
    }
}
