package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.ProductItem;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIMainPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.watcher.WebDriverExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(WebDriverExtension.class)
public class TICatalogAndSortingTest {
    WebDriver driver;

    public TICatalogAndSortingTest(WebDriver driver) {
        this.driver = driver;
    }

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

        ProductItem highestPriceProductItem = acousticPage.getProductItemByIndex(1);

        Assertions.assertTrue(lowestPriceProductItem.price() <= highestPriceProductItem.price(),
                "Sorting verification failed: The lowest price product (" + lowestPriceProductItem.price() +
                ") is greater than the highest price product (" + highestPriceProductItem.price() + ").");
    }
}
