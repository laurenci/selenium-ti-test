package com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.driver;

import com.epam.training.student_maksym_mishchuk.selenium_ti_test_task.tool.Context;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverProvider {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = setBrowser(System.getProperty("browser"));
            driver.manage().window().maximize();
            driver = new EventFiringDecorator<>(new LocalListener()).decorate(driver);
        }
        return driver;
    }

    private static WebDriver setBrowser(String browserName) {
        checkBrowserName(browserName);
        URL gridURL = getGridUrl();
        Capabilities options =  getCapabilitiesByBrowserName(browserName);
        return new RemoteWebDriver(gridURL, options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static void checkBrowserName(String browserName) {
        if (browserName == null || browserName.isEmpty())
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
    }

    private static URL getGridUrl() {
        try {
            return new URL(Context.filePropertiesReader.getProperty("grid.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Capabilities getCapabilitiesByBrowserName(String browserName) {
        return switch (browserName) {
            case "chrome" ->  prepareChromeOptions();
            case "firefox" -> new FirefoxOptions();
            case "edge" -> new EdgeOptions();
            default -> throw new RuntimeException("Unsupported browser: " + System.getProperty("browser"));
        };
    }

    private static ChromeOptions prepareChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        return options;
    }
}
