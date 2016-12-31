package by.htp.belavia.pages;


import by.htp.belavia.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ark on 29.12.2016.
 */
public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass(description = "Initiate browser and open page")
    public void setUp() throws Exception {
        driver = DriverSingleton.getDriver();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @AfterClass(description = "Stop browser")
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "Verify the Home page URL")
    public void testVerifyUrl() throws Exception {
        assertTrue(homePage.verifyUrl());
    }
    @Test(description = "Check FromCity field displayed")
    public void testFromCityField() throws Exception {
        assertTrue(homePage.displayedFromCityField());
    }
    @Test(description = "Check ToCity field displayed")
    public void testToCityField() throws Exception {
        assertTrue(homePage.displayedToCityField());
    }
    @Test(description = "Check DepartureDate field displayed")
    public void testDepartureDateField() throws Exception {
        assertTrue(homePage.displayedDepartureDateField());
    }
    @Test(description = "Check ReturnDate field displayed")
    public void testReturnDateField() throws Exception {
        assertTrue(homePage.displayedReturnDateField());
    }
    @Test(description = "Check Submit button displayed")
    public void testSubmitButton() throws Exception {
        assertTrue(homePage.displayedSubmitButton());
    }
}