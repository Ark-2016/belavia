package by.htp.belavia.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ark on 26.12.2016.
 */
public class ChoicePage extends Page {
    private final Logger logger = LogManager.getLogger(ChoicePage.class);

    private final String flightString = "#outbound>h2";
    private final By flightSelector = By.cssSelector(flightString);
//    @FindBy(css = flightString)
//    private WebElement fromToLabel;
//    @FindBy(css = "#outbound .col-mb-5.text-right>a")
//    private WebElement tariffCalendar;
    private WebElement fromToLabel;
    private WebElement tariffCalendar;

    public ChoicePage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(this.driver, driver);
    }

    @Override
    public void open() {
        try {
            (new WebDriverWait(driver, 5)).
                    until(ExpectedConditions.visibilityOfElementLocated(flightSelector));
        } catch(WebDriverException e) {}
        fromToLabel = driver.findElement(By.cssSelector(flightString));
        tariffCalendar = driver.findElement(By.cssSelector("#outbound .col-mb-5.text-right>a"));
    }

    public void submitTariffCalendar() {
        logger.info("Flight " + fromToLabel.getText());
        tariffCalendar.click();
        logger.info("TariffCalendar submitted");
        logger.info("----------------------------");
    }

    public boolean displayedFlightRoute() {
        return fromToLabel.isDisplayed();
    }
    public boolean displayedTariffCalendarLabel() {
        return tariffCalendar.isDisplayed();
    }
}

