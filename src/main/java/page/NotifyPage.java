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
public class NotifyPage extends BasePage implements LoadableComponent {

    @FindBy(css = ".has-padding-right-mobile")
    private WebElement searchFilter;

    @FindBy(linkText = "Налаштування")
    private WebElement settingBtn;

    @Step
    public SettingPage goToSettingPage() {
        log.info("Go to Setting Page");
        settingBtn.click();
        return new SettingPage();
    }

    @Step
    public NotifyPage verifyPageLoaded() {
        log.info("Check that we are on that page.");
        assertThat(isLoaded()).as("Page didn't load!").isTrue();
        return this;
    }


    @Override
    public boolean isLoaded() {
        return ElementActions.isDisplayed(searchFilter);
    }
}
