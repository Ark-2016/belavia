package by.htp.belavia.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ark on 24.12.2016.
 */
public class TariffPage extends Page {
    private final Logger logger = LogManager.getLogger(TariffPage.class);

    @FindBy(css = ".h-outbound.hidden-xs.clear .date")
    private List<WebElement> departureDateList = null;
    @FindBy(css = ".h-inbound.hidden-xs.clear .date")
    private List<WebElement>returnDateList = null;
    @FindBy(css = "#matrix .content")
    private List<WebElement> priceList = null;

    private final By departureDateButtonSelector = By.cssSelector(".h-outbound.hidden-xs.clear .nav-right>a");
    private final By returnDateButtonSelector = By.cssSelector(".h-inbound.hidden-xs.clear .nav-left>a");
    private final By departureDateSelector = By.cssSelector(".h-outbound.hidden-xs.clear .column.first .date");
    private final By returnDateSelector = By.cssSelector(".h-inbound.hidden-xs.clear .column.first .date");

    public TariffPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        try {
            (new WebDriverWait(driver, 2)).
                    until(ExpectedConditions.visibilityOfElementLocated(returnDateSelector));
        } catch (WebDriverException e) {}
    }

    public void changeDataSheet() {
        nextSheet(returnDateSelector, returnDateButtonSelector);
        nextSheet(departureDateSelector, departureDateButtonSelector);
    }

    public int captureResults() {
        ArrayList<String> departureDate = new ArrayList<>();
        ArrayList<String> returnDate = new ArrayList<>();
        ArrayList<String> fare = new ArrayList<>();

        for (WebElement el: departureDateList) {
            departureDate.add(el.getText());
        }
        for (WebElement el: returnDateList) {
            returnDate.add(el.getText());
        }
        int ind= 0;
        for (WebElement el: priceList) {
            if(ind++ % 8 == 0)
                fare.add(el.getText());
        }

        // minimal fare flights of the week
        int len = departureDate.size() < returnDate.size() ? departureDate.size() : returnDate.size();
        len = len <= fare.size() ? len : fare.size();
        String minFare = "9999,99 EUR";
        for (int i = 0; i < len; ++i) {
            String currentFare = fare.get(i);
            if("0".compareTo(currentFare) < 0 && minFare.compareTo(currentFare) > 0)
                minFare = currentFare;
        }
        int flightCount = 0;
        for (int i = 0; i < len; ++i) {
            if(0 == minFare.compareTo(fare.get(i))) {
                logger.info(departureDate.get(i) + "   " + fare.get(i) + "   " + returnDate.get(i));
                ++flightCount;
            }
        }
        logger.info("----------------------------");

        return flightCount;
    }

    private void nextSheet(final By dateSelector, By dateButtonSelector) {
        final int timeOut = 5;
        try {
            (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(dateSelector));
        } catch(WebDriverException e) {}
        try {
            (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(dateButtonSelector));
        } catch(WebDriverException e) {}

        final String date = driver.findElement(dateSelector).getText();
        driver.findElement(dateButtonSelector).click();

        (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(dateSelector));
                WebElement webElement = d.findElement(dateSelector);
                if (null != webElement) {
                    try {
                        return !date.contentEquals(webElement.getText());
                    } catch(WebDriverException e) {
                        return true;
                    }
                }
                return true;
            }
        });
    }

}
