package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.util.converter;

import java.util.Arrays;
import java.util.List;

public class CSVRawDataToPairFilters {
    public record Pair(String section, String filter) {}
    public static List<Pair> convert(String rawData, String splitter) {
        return Arrays.stream(rawData.split(splitter))
                .map(s -> s.split(","))
                .map(arr -> new Pair(arr[0], arr[1]))
                .toList();
    }
}
