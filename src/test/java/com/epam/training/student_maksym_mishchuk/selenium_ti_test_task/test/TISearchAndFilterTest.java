package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.ProductItem;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.page.ti.TIMainPage;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.WebDriverProvider;
import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter.CSVRawDataToPairFilters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class TISearchAndFilterTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.getDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/search_and_filter/data.csv")
    void testCatalogAndSorting(String searchData, String expectedCategory, String filtersRawData, int from, int to) {
        var filters = CSVRawDataToPairFilters.convert(filtersRawData, ";");

        var searchBarResults = new TIMainPage(driver)
                .openPage()
                .search(searchData);

        List<String> popularResults = searchBarResults.getPublicCategoryNames();

        Assertions.assertTrue(
                popularResults.contains(expectedCategory),
                "Search results do not contain the expected category 'Ноутбуки'. Found categories: " + popularResults
        );

        var itemPage = searchBarResults
                .goToThePopularCategory(expectedCategory);

        for (CSVRawDataToPairFilters.Pair filter : filters) {
            itemPage = itemPage
                    .applyFilter(filter.section(), filter.filter());
        }

        List<ProductItem> result = itemPage
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
        WebDriverProvider.quitDriver();
    }
}
