package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.test.watcher;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.driver.WebDriverProvider;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebDriverExtension implements ParameterResolver, AfterEachCallback {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverExtension.class);

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return WebDriverProvider.getDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getExecutionException().ifPresent(throwable -> {
            logger.error(throwable.getMessage());
            takeScreenshot(context);
        });
        WebDriverProvider.quitDriver();
    }

    private void takeScreenshot(ExtensionContext context) {
        TakesScreenshot driver = (TakesScreenshot) WebDriverProvider.getDriver();
        Path screenshotSource = driver.getScreenshotAs(OutputType.FILE).toPath();
        try {
            Path dir = initScreenshotsDirectory();
            Path path = initScreenshotFile(dir, context.getDisplayName(), "png");
            saveScreenshot(screenshotSource, path);
            logger.error("Screenshot saved successfully at: {}", path.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path initScreenshotsDirectory() throws IOException {
        String dirName = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path screenshotsDir = Path.of("target", "screenshots", dirName);
        Files.createDirectories(screenshotsDir);
        return screenshotsDir;
    }

    private Path initScreenshotFile(Path dir, String fileName, String fileExtension) throws IOException {
        String screenshotTimePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        String filePath = String.format("%s [%s] (%s).%s", screenshotTimePart, System.getProperty("browser"), fileName, fileExtension);
        Path screenshotPath = dir.resolve(filePath);
        Files.createFile(screenshotPath);
        return screenshotPath;
    }

    private void saveScreenshot(Path sourcePath, Path destinationPath) throws IOException {
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
