package action;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class ElementActions {

    public static boolean isDisplayed(WebElement locator) {
        try {
            WebElement element = locator;
            return element != null && element.isDisplayed();
        } catch (WebDriverException | NoSuchElementException noSuchElement) {
            return false;
        }
    }
}
