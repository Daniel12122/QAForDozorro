package test;

import base.BaseTest;
import factory.UserFactory;
import model.User;
import org.testng.annotations.Test;
import page.MainPage;

public class PauseTest extends BaseTest {

    User user = UserFactory.getDefaultUser();

    @Test
    void pausesNotificationChannel() {
        new MainPage()
                .goToLoginPage()
                .clickBtnLogInWithGoogle()
                .logInGoogleAccount(user)
                .verifyPageLoaded()
                .goToNotifyPage()
                .goToSettingPage()
                .clickActivateGoogleBtn()
                .openGoogleEmail()
                .goToNotifyPage()
                .goToSettingPage()
                .verifyActivatedNotificationChannel()
                .clickPauseGoogleBtn()
                .verifyPausedGoogleEmail();
    }
}
