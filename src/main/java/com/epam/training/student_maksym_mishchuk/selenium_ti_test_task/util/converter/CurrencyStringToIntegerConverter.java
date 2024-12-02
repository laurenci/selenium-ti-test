package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter;

public class CurrencyStringToIntegerConverter {
    public static Integer convert(String s) {
        return Integer.valueOf(
                s
                        .trim()
                        .substring(0, s.length() - 1)
                        .replaceAll(" ", "")
        );
    }
}
