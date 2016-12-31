package by.htp.belavia.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ark on 24.12.2016.
 */
public class HomePage extends Page {
    private final Logger logger = LogManager.getLogger(HomePage.class);
    private final String BASE_URL = "https://belavia.by/home/";

    private final String fromCity = "Минск (MSQ), BY";
    private final String jsFromCity =
            "var s = document.getElementById('OriginLocation'); s.type = 'visible'; s.value = 'MSQ';";

    private final String toCity = "Сочи (AER), RU";
    private final String jsToCity =
            "var s = document.getElementById('DestinationLocation'); s.type = 'visible'; s.value = 'AER';";

    private final String jsDepartureDate =
            "var s = document.getElementById('DepartureDate'); s.type = 'visible'; s.value = '2017-02-27';";
    private final String jsDepartureDateDatapicker =
            "var s = document.getElementById('DepartureDate_Datepicker'); s.value = '2017-02-27';";

    private final String jsReturnDate =
            " var s= document.getElementById('ReturnDate');s.type = 'visible'; s.value = '2017-03-06';";
    private final String jsReturnDateDatapicker =
            "var s= document.getElementById('ReturnDate_Datepicker'); s.value = '2017-03-06';";

    @FindBy(id = "OriginLocation")
    private WebElement fromCityHiddenField;
    @FindBy(id = "OriginLocation_Combobox")
    private WebElement fromCityField;

    @FindBy(id = "DestinationLocation")
    private WebElement toCityHiddenField;
    @FindBy(id = "DestinationLocation_Combobox")
    private WebElement toCityField;

    @FindBy(id = "DepartureDate")
    private WebElement departureDateField;
    @FindBy(id = "DepartureDate_Datepicker")
    private WebElement departureDateDatepicker;

    @FindBy(id = "ReturnDate")
    private WebElement returnDateField;
    @FindBy(id = "ReturnDate_Datepicker")
    private WebElement returnDateDatepicker;

    @FindBy(css = "#step-2 button.btn-large.btn.btn-b2-green.ui-corner-all")
    private WebElement submitButton;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.get(BASE_URL);
        try {
            (new WebDriverWait(driver, 5)).
                    until(ExpectedConditions.visibilityOfElementLocated(By.id("OriginLocation_Combobox")));
        } catch(WebDriverException e) {}
        fromCityField.click();
        logger.info("open");
    }

    public void fillInSearchForm() {
        try {
            (new WebDriverWait(driver, 5)).
                    until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnDate_Datepicker")));
        } catch(WebDriverException e) {}
        setFromCity();
        setToCity();
        setDepartureDate();
        setReturnDate();
        logger.info("filled in the search form");
    }

    public void submit() {
        submitButton.click();
        logger.info("submit searching");
    }

    public boolean verifyUrl() {
        return  driver.getCurrentUrl().contentEquals(BASE_URL);
    }
    public boolean displayedFromCityField() {
        return  fromCityField.isDisplayed();
    }
    public boolean displayedToCityField() {
        return  toCityField.isDisplayed();
    }
    public boolean displayedDepartureDateField() {
        return departureDateDatepicker.isDisplayed();
    }
    public boolean displayedReturnDateField() {
        return returnDateDatepicker.isDisplayed();
    }
    public boolean displayedSubmitButton() {
        return submitButton.isDisplayed();
    }

    private void setFromCity() {
        ((JavascriptExecutor) driver).executeScript(jsFromCity, fromCityHiddenField);
        fromCityField.sendKeys(fromCity);
    }

    private void setToCity() {
        ((JavascriptExecutor) driver).executeScript(jsToCity, toCityHiddenField);
        toCityField.sendKeys(toCity);
}

    private void setDepartureDate() {
        ((JavascriptExecutor) driver).executeScript(jsDepartureDate, departureDateField);
        ((JavascriptExecutor) driver).executeScript(jsDepartureDateDatapicker, departureDateDatepicker);
    }

    private void setReturnDate() {
        ((JavascriptExecutor) driver).executeScript(jsReturnDate, returnDateField);
        ((JavascriptExecutor) driver).executeScript(jsReturnDateDatapicker, returnDateDatepicker);
    }

}
