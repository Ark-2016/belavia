package by.htp.belavia.logic;

import by.htp.belavia.driver.DriverSingleton;
import by.htp.belavia.pages.ChoicePage;
import by.htp.belavia.pages.HomePage;
import by.htp.belavia.pages.TariffPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/**
 * Created by Ark on 24.12.2016.
 */
public class TicketBox {
    private int weekNumber;

    private WebDriver driver;
    private HomePage homePage;
    private ChoicePage choicePage;
    private TariffPage tariffPage;

    public TicketBox(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void open() {
        driver = DriverSingleton.getDriver();
        homePage = new HomePage(driver);
        homePage.open();
        homePage.fillInSearchForm();
        homePage.submit();
        choicePage = new ChoicePage(driver);
        choicePage.open();
        choicePage.submitTariffCalendar();
        tariffPage = new TariffPage(driver);
        tariffPage.open();
    }

    public int ticketSearching() {
        int flightCount = 0;
        try {
            flightCount += tariffPage.captureResults();
            for (int i = 1; i < weekNumber; i++) {
                tariffPage.changeDataSheet();
                flightCount += tariffPage.captureResults();
            }
        } catch(WebDriverException e) {
            e.printStackTrace();
        }
        return flightCount;
    }

}
