package by.htp.belavia.pages;

import by.htp.belavia.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Ark on 29.12.2016.
 */
public class ChoicePageTest {

    private WebDriver driver;
    private HomePage homePage;
    private ChoicePage choicePage;

    @BeforeClass(description = "Initiate browser, fill in and submit home page and open choice page")
    public void setUp() throws Exception {
        driver = DriverSingleton.getDriver();
        homePage = new HomePage(driver);
        homePage.open();
        homePage.fillInSearchForm();
        homePage.submit();
        choicePage = new ChoicePage(driver);
        choicePage.open();
    }

    @AfterClass(description = "Stop browser")
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "Verify Flight route")
    public void testVerifyFlightRoute() throws Exception {
        assertTrue(choicePage.displayedFlightRoute());
    }
    @Test(description = "Check TariffCalendar label displayed")
    public void testTariffCalendarLabelDisplayed() throws Exception {
        assertTrue(choicePage.displayedTariffCalendarLabel());
    }
}