package page;

import action.ElementActions;
import base.BasePage;
import base.LoadableComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MainPage extends BasePage implements LoadableComponent {
    @FindBy(css = ".delete")
    private WebElement closePoll;

    @FindBy(css = ".login_link")
    private WebElement singInBtn;

    @FindBy(css = ".c-header__logo")
    private WebElement logo;

    @FindBy(css = ".caret")
    private WebElement dropdownName;

    @FindBy(linkText = "Сповіщення")
    private WebElement notifyBtn;

    @Step
    public LoginPage goToLoginPage() {
        log.info("Close Poll");
        closePoll.click();
        verifyPageLoaded();
        log.info("Go to Login Page");
        singInBtn.click();
        return new LoginPage();
    }

    @Step
    public NotifyPage goToNotifyPage() {
        log.info("Click on dropdown near Name");
        dropdownName.click();
        log.info("Go to Notify Page");
        notifyBtn.click();
        return new NotifyPage();
    }

    @Step
    public MainPage verifyPageLoaded() {
        log.info("Check that we are on that page.");
        assertThat(isLoaded()).as("Page didn't load!").isTrue();
        return this;
    }


    @Override
    public boolean isLoaded() {
        return ElementActions.isDisplayed(logo);
    }
}
