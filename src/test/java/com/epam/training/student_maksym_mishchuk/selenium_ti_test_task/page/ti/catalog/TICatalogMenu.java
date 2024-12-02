package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.catalog;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TICatalogMenu extends AbstractInnerPage {
    private final Actions actions = new Actions(driver);

    @FindBy(xpath = "//li[contains(@class, 'flex items-center')]/a[@href='/ua/audio/']")
    private WebElement audio;

    public TICatalogMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TICatalogAudioMenu openAudioMenu() {
        actions.moveToElement(audio).perform();
        return new TICatalogAudioMenu(driver);
    }
}
