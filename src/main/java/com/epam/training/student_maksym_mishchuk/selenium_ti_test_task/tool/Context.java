package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool;

public class Context {
    public static final FilePropertiesReader filePropertiesReader = new FilePropertiesReader(
            Context.class.getClassLoader().getResource(
                    System.getProperty("propertiesPath")
            ).getFile()
    );
}
