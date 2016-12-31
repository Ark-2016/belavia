package by.htp.belavia.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ark on 24.12.2016.
 */
public abstract class Page {
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void open();
}
