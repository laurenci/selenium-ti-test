package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.ProductItem;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIMainPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.WebDriverProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class TICatalogAndSortingTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.getDriver();
    }

    @SneakyThrows
    @Test
    void testCatalogAndSorting() {
        var acousticPage = new TIMainPage(driver)
                .openPage()
                .openCatalogMenu()
                .openAudioMenu()
                .openAcousticCatalog()
                .sortByPriceAscending();

        ProductItem lowestPriceProductItem = acousticPage.getProductItemByIndex(1);

        acousticPage = acousticPage.sortByPriceDescending();

        Thread.sleep(500);

        ProductItem highestPriceProductItem = acousticPage.getProductItemByIndex(1);

        Assertions.assertTrue(lowestPriceProductItem.price() <= highestPriceProductItem.price(),
                "Sorting verification failed: The lowest price product (" + lowestPriceProductItem.price() +
                ") is greater than the highest price product (" + highestPriceProductItem.price() + ").");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
