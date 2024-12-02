package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.ProductItem;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.AbstractPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.VisibleAjaxElementLocatorFactory;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter.CurrencyStringToIntegerConverter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TIListOfItemsPage extends AbstractPage {
    private final String baseUrl = "https://ti.ua/";

    @FindBy(xpath = "//div[@id='sorting']/div[@class='panel']")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//div[@id='sorting']/descendant::li[text()='спочатку дешевше']")
    private WebElement sortingPriceAscending;

    @FindBy(xpath = "//div[@id='sorting']/descendant::li[text()='спочатку дорожче']")
    private WebElement sortingPriceDescending;

    @FindBy(className = "min-price")
    private WebElement minPrice;

    @FindBy(className = "max-price")
    private WebElement maxPrice;

    @FindBy(css = "div.filter-box")
    private List<WebElement> filterBoxes;

    private final String productItemsXPath = "//div[contains(@class, 'catalog-list')]/div[@class='product-item']";
    private final String productItemByIndexPattern = "//div[contains(@class, 'catalog-list')]/div[@class='product-item'][%d]";

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public TIListOfItemsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new VisibleAjaxElementLocatorFactory(driver, 5), this);
    }

    @Override
    protected AbstractPage openPage() {
        driver.get(baseUrl);
        return this;
    }

    public TIListOfItemsPage openPage(String specificList) {
        driver.get(baseUrl.concat(specificList));
        return this;
    }

    public TIListOfItemsPage sortByPriceAscending() {
        sortingDropdown.click();
        sortingPriceAscending.click();
        return this;
    }

    public ProductItem getProductItemByIndex(int index) {
        return webElementToProductItem(
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(productItemByIndexPattern.formatted(index))
                ))
        );
    }

    @SneakyThrows
    public List<ProductItem> getProductItems() {
        Thread.sleep(500);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(productItemsXPath))).stream()
                .map(this::webElementToProductItem)
                .toList();
    }

    private ProductItem webElementToProductItem(WebElement element) {
        return new ProductItem(
                element.findElement(By.className("product-name")).getText(),
                CurrencyStringToIntegerConverter.convert(element.findElement(By.className("price")).getText())
        );
    }

    public TIListOfItemsPage sortByPriceDescending() {
        sortingDropdown.click();
        sortingPriceDescending.click();
        return this;
    }

    public TIListOfItemsPage applyFilter(String section, String filter) {
        filterBoxes.stream()
                .filter(element -> element
                        .findElement(By.className("filter-box__title")).getText()
                        .trim().equalsIgnoreCase(section))
                .flatMap(element -> element.findElements(By.className("input-checkbox")).stream())
                .filter(element -> element.getText().trim().contains(filter))
                .forEach(element -> wait.until(ExpectedConditions.elementToBeClickable(element)).click());
        return this;
    }

    @SneakyThrows
    public TIListOfItemsPage setPriceRange(int from, int to) {
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(minPrice));
        clearInput(minPrice);
        minPrice.sendKeys(Integer.toString(from));
        wait.until(ExpectedConditions.elementToBeClickable(maxPrice));
        clearInput(maxPrice);
        maxPrice.sendKeys(Integer.toString(to));

        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='price-thump-2']"))).click();

        return this;
    }

    private void clearInput(WebElement input) {
        if (SystemUtils.IS_OS_MAC)
            input.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        else if (SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_LINUX)
            input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        else
            throw new UnsupportedOperationException("This method supports only three OSs!");
    }
}
