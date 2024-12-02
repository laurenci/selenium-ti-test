package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.model.iPhone;

public class CSVRawDataToIPhoneConverter {
    public static iPhone convert(String rawData) {
        String[] rawDataArr = rawData.split(",");
        return new iPhone(rawDataArr[0], rawDataArr[1], rawDataArr[2]);
    }
}
