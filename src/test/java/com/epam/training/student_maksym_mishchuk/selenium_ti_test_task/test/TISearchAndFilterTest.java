package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.ProductItem;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIMainPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class TISearchAndFilterTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.getDriver();
    }

    @Test
    void testCatalogAndSorting() {
        String search = "Нувбук";
        int from = 38620;
        int to = 59999;

        var searchBarResults = new TIMainPage(driver)
                .openPage()
                .search(search);

        List<String> popularResults = searchBarResults.getPublicCategoryNames();

        Assertions.assertTrue(
                popularResults.contains("Ноутбуки"),
                "Search results do not contain the expected category 'Ноутбуки'. Found categories: " + popularResults
        );

        List<ProductItem> result = searchBarResults
                .goToThePopularCategory("Ноутбуки")
                .applyFilter("У категоріях", "Ноутбуки")
                .applyFilter("Бренд", "Acer")
//                .setPriceRange(from, to)
                .getProductItems();

        Assertions.assertEquals(
                0,
                result.stream()
                .filter(productItem -> productItem.price() < from || productItem.price() > to)
                .count(),
                "Some products have prices outside the expected range (" + from + " to " + to + ")."
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
