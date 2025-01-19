package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.provider;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.Context;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.stream.Stream;

public class CSVArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return readCsvFile(getPath(context))
                .stream()
                .map(Arguments::of);
    }

    private Path getPath(ExtensionContext context) throws URISyntaxException {
        String className = getClassName(context);
        String pathPattern = Context.filePropertiesReader.getProperty(className);
        String path = pathPattern.formatted(System.getProperty("environment"));
        return Path.of(getURI(path));
    }

    private URI getURI(String path) throws URISyntaxException {
        return this.getClass().getResource(path).toURI();
    }

    private String getClassName(ExtensionContext context) {
        return context.getTestClass().orElseThrow().getSimpleName();
    }

    private List<String[]> readCsvFile(Path path) throws CsvException, IOException {
        try (
                Reader reader = Files.newBufferedReader((path));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            return csvReader.readAll();
        }
    }
}
