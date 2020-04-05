package base;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public WebDriver driver = DriverManager.getInstance().getDriver();
    public String url = " https://dev.dozorro.work/";

    @BeforeTest
    public void openSearchPage() {
        driver.get(url);
    }

    @AfterTest
    public void close() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            driver = null;
        }
    }
}
