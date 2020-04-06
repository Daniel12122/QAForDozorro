package page;

import action.ElementActions;
import base.BasePage;
import base.LoadableComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import manager.DriverManager;
import model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.testng.Assert.assertTrue;

@Slf4j
public class GooglePage extends BasePage implements LoadableComponent {

    @FindBy(css = "[autocomplete=\"username\"]")
    private WebElement inputUsernameGoogle;

    @FindBy(css = "[autocomplete=\"current-password\"]")
    private WebElement inputPasswordGoogle;

    @FindBy(css = "#identifierNext")
    private WebElement goToPasswordBtnGoogle;

    @FindBy(css = "#passwordNext")
    private WebElement logInBtnGoogle;

    @FindBy(css = "img.gb_ua")
    private WebElement logoGoogleEmail;

    @FindBy(css = "[id=\":2t\"]")
    private WebElement messageActivateGoogleEmail;

    @FindBy(xpath = "//a[contains(text(),'https:')]")
    private WebElement linkActivateGoogleEmail;

    @FindBy(css = "[id=':5h8']")
    private WebElement showBtn;

    @FindBy(css = ".c-header__logo")
    private WebElement logo;


    @Step
    public MainPage logInGoogleAccount(User user) {
        log.info("Input Username - " + user.getEmail());
        inputUsernameGoogle.sendKeys(user.getEmail());
        log.info("Click in next button");
        goToPasswordBtnGoogle.click();
        log.info("Input password - " + user.getPassword());
        inputPasswordGoogle.sendKeys(user.getPassword());
        log.info("Click in SingIn button");
        logInBtnGoogle.click();
        return new MainPage();
    }

    @Step
    public MainPage openGoogleEmail() {
        log.info("Open Google Email account");
        DriverManager.getInstance().getDriver().get("https://mail.google.com/mail/u/0/#inbox");
        verifyPageLoaded();
        log.info("Wait for email");
        await()
                .atMost(20, SECONDS)
                .pollInterval(4, SECONDS)
                .untilAsserted(() -> assertTrue(logoGoogleEmail.isDisplayed()));
        log.info("Refresh website");
        driver.navigate().refresh();
        log.info("Open mail with activated code");
        messageActivateGoogleEmail.click();
        log.info("Open link for activated Google email");
        String linkActivateGoogleEmailText = linkActivateGoogleEmail.getText();
        driver.get(linkActivateGoogleEmailText);
        log.info("Open Main Page");
        logo.click();
        return new MainPage();
    }

    @Step
    public GooglePage verifyPageLoaded() {
        log.info("Check that we are on that page.");
        assertThat(isLoaded()).as("Page didn't load!").isTrue();
        return this;
    }


    @Override
    public boolean isLoaded() {
        return ElementActions.isDisplayed(logoGoogleEmail);
    }
}
