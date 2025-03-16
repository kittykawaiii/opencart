package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC001AccountRegistrationTest extends BaseClass {

    @Test
    void verifyAccountRegistration() {
        logger.info("*** Starting TC001AccountRegistrationTest ***");

        try {
            // Initialize HomePage and perform actions
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account Link");
            hp.clickRegister();
            logger.info("Clicked on Register Link");

            // Initialize AccountRegistrationPage and provide user details
            logger.info("Providing user details");
            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
            regPage.setFirstName(randomString().toUpperCase());
            regPage.setLastName(randomString().toUpperCase());
            regPage.setEmail(randomString() + "@gmail.com");

            String password = randomAlphaNumberic();
            regPage.setPassword(password);

            regPage.setPrivacyPolicy();
            regPage.clickContinue();

            logger.info("Validating expected message");
            String confMsg = regPage.getConfirmationMsg();
            logger.info("Actual confirmation message: " + confMsg);

            Assert.assertEquals(confMsg, "Your Account Has Been Created!");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("*** Finished TC001AccountRegistrationTest ***");
    }
}
