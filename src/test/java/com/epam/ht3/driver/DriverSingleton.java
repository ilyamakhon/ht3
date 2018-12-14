package com.epam.ht3.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali_Shulha on 20-Oct-16.
 */
public class DriverSingleton {

    private static final Logger logger = LogManager.getRootLogger();
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_EXE_PATH = "src\\geckodriver\\geckodriver.exe";
    private static WebDriver driver;

    private DriverSingleton() {
    }

    ;


    public static WebDriver getDriver() {
        if (null == driver) {
            System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
            driver = new FirefoxDriver();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            logger.info("Browser started");
        }

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
