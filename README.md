# Test Cases for TI.UA Website

## T-1: Check Trade-In Functionality

### Steps to Reproduce:
1. Navigate to the page: https://ti.ua/ua/trade-in/
2. Open the form "Який у вас пристрій?".
3. Fill out the form with this device: iPhone 14, 128 ГБ, Midnight (MY_DEVICE).
4. Record the price.
5. Open the form "На який девайс хочете поміняти?".
6. Fill out the form with this device: iPhone 16 Pro Max, 512 ГБ, Black Titanium (NEW_DEVICE).
7. Record the price and calculate the subtraction result.
8. Verify that the subtraction of NEW_DEVICE and MY_DEVICE matches the expected result.

### Expected Behavior:
- The subtraction should be calculated correctly.

---

## T-2: Check Catalog Menu and Sorting Functionality

### Steps to Reproduce:
1. Navigate to the page: https://ti.ua/ua/
2. Hover over the catalog menu.
3. Hover over “Аудіо та навушники”.
4. Click on “Акустика”.
5. Click on “Портативна акустика”.
6. Sort the displayed items by price (from low to high).
7. Record the first item (ITEM_LOW).
8. Sort the displayed items by price (from high to low).
9. Record the first item (ITEM_HIGH).
10. Verify that the price of ITEM_LOW is less than the price of ITEM_HIGH.

### Expected Behavior:
- The price of ITEM_LOW should be less than the price of ITEM_HIGH.

---

## T-3: Check Filter and Search Functionality

### Steps to Reproduce:
1. Navigate to the page: https://ti.ua/ua/
2. Enter “Нувбук” in the search bar.
3. Verify that the “Popular Categories” section contains “Ноутбуки”.
4. Click on “Ноутбуки”.
5. In the left sidebar, click on “Ноутбуки”.
6. Select “Acer” as the brand.
7. Set the price range from 38620 to 59999.
8. Verify that the result is not empty.
    - If the result is empty, verify that the appropriate message is displayed and end the test case.
    - If the result is not empty, continue with the test execution.
9. Verify that all item prices in the results list fall within the specified range.

### Expected Behavior:
- The “Popular Categories” section should display “Ноутбуки” after the search.
- If no items match the filter, an appropriate message should be displayed.
- If matching items are found, all their prices should be within the specified range.  
