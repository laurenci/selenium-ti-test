package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.search;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractInnerPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIListOfItemsPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.VisibleAjaxElementLocatorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class TISearchBar extends AbstractInnerPage {
    @FindBy(xpath = "//span[text()='Популярні категорії']/following-sibling::ul/li/a")
    private List<WebElement> popularCategories;

    public TISearchBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new VisibleAjaxElementLocatorFactory(driver, 5), this);
    }

    public List<String> getPublicCategoryNames() {
        return popularCategories.stream()
                .map(WebElement::getText)
                .toList();
    }

    public TIListOfItemsPage goToThePopularCategory(String categoryName) {
        popularCategories.stream()
                .filter(webElement -> webElement.getText().equals(categoryName))
                .findFirst()
                .orElseThrow(NoSuchElementException::new).click();
        return new TIListOfItemsPage(driver);
    }
}
