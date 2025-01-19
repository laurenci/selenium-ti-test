package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.exception.FilePropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FilePropertiesReader {
    Logger logger = LoggerFactory.getLogger(FilePropertiesReader.class);

    private final Properties properties = new Properties();

    public FilePropertiesReader(String fileName) {
        try {
            properties.load(
                    new BufferedReader(new FileReader(
                           fileName
                    ))
            );
        } catch (IOException e) {
            throw new FilePropertyException(e);
        }
    }

    public String getProperty(String key) {
        String readProperty = properties.getProperty(key);
        logger.debug("Read by key '{}' property: {}" , key, readProperty);
        return readProperty;
    }
}
