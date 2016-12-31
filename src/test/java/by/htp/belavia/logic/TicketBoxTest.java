package by.htp.belavia.logic;

import by.htp.belavia.driver.DriverSingleton;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Ark on 29.12.2016.
 */
public class TicketBoxTest {

    private static final int weekNumber = 5;
    private TicketBox ticketBox;

    @BeforeMethod(description = "Open the Ticket box")
    public void setUp() throws Exception {
        ticketBox = new TicketBox(weekNumber);
        ticketBox.open();
    }

    @AfterMethod(description = "Stop web driver")
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "Verify success of the search ")
    public void testTicketSearching() throws Exception {
        assertTrue(ticketBox.ticketSearching() > 0);
    }

}