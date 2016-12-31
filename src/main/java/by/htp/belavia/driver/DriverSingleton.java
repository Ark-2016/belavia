package by.htp.belavia.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ark on 24.12.2016.
 */
public class DriverSingleton {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverSingleton.class);
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = "src\\main\\resources\\geckodriver.exe";

    public static WebDriver getDriver(){
        if (null == driver){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities
                    ("firefox", "50.1.0", Platform.WIN8_1);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.startup.homepage","about:blank");
            desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
            System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_GECKODRIVER_EXE_PATH);

            driver = new FirefoxDriver(desiredCapabilities);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            logger.info("Browser started");
        }
        return driver;
    }

    public static void closeDriver(){
        logger.info("Browser quited\n");
        driver.quit();
        driver = null;
    }
}
