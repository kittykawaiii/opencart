package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC001AccountRegistrationTest extends BaseClass {
    @Test
    void verifyAccountRegistration(){
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        regPage.setFirstName(randomString().toUpperCase());
        regPage.setLastName(randomString().toUpperCase());
        regPage.setEmail(randomString()+"@gmail.com");

        String password = randomAlphaNumberic();
        regPage.setPassword(password);

        regPage.setPrivacyPolicy();
        regPage.clickContinue();

        String confMsg = regPage.getConfirmationMsg();
        Assert.assertEquals(confMsg,"Your Account Has Been Created!");
    }
}
