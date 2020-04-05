package page;

import action.ElementActions;
import base.BasePage;
import base.LoadableComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SettingPage extends BasePage implements LoadableComponent {

    @FindBy(css = ".is-outlined-button")
    private WebElement activationEmailBtn;

    @FindBy(css = ".is-telegram-button")
    private WebElement telegramBtn;

    @FindBy(css = ".icon-pause")
    private WebElement pauseGoogleBtn;

    @FindBy(css = ".is-outlined-button")
    private WebElement restorationBtn;

    @FindBy(xpath = "//div[@id=\"review_form4\"]//a[contains(text(),'Ок')]")
    private WebElement acceptPauseGoogle;

    @Step
    public GooglePage clickActivateGoogleBtn() {
        verifyPageLoaded();
        log.info("Click on activate button");
        activationEmailBtn.click();
        return new GooglePage();
    }

    @Step
    public SettingPage clickPauseGoogleBtn() {
        verifyPageLoaded();
        log.info("Click on pause button");
        pauseGoogleBtn.click();
        log.info("Click on pause accept");
        acceptPauseGoogle.click();
        return this;
    }

    @Step
    public SettingPage verifyActivatedNotificationChannel() {
        log.info("Check that we are on that page and an error message appears.");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(isLoaded()).as("Page didn't load!").isTrue();
        assertions.assertThat(pauseGoogleBtn.isDisplayed()).as("Notification channel not activated").isTrue();
        assertions.assertAll();
        return this;
    }

    @Step
    public SettingPage verifyPageLoaded() {
        log.info("Check that we are on that page.");
        assertThat(restorationBtn.isDisplayed()).as("Email not paused!").isTrue();
        return this;
    }

    @Step
    public SettingPage verifyPausedGoogleEmail() {
        log.info("Check paused Google email.");
        assertThat(isLoaded()).as("Page didn't load!").isTrue();
        return this;
    }

    @Override
    public boolean isLoaded() {
        return ElementActions.isDisplayed(telegramBtn);
    }
}
