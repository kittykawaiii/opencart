package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC001AccountRegistrationTest extends BaseClass {
    @Test
    void verifyAccountRegistration(){
        logger.info("***Starting TC001AccountRegistrationTest***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Click on My Account Link");
            hp.clickRegister();
            logger.info("Click on Register Link");

            logger.info("Providing user details");
            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
            regPage.setFirstName(randomString().toUpperCase());
            regPage.setLastName(randomString().toUpperCase());
            regPage.setEmail(randomString() + "@gmail.com");

            String password = randomAlphaNumberic();
            regPage.setPassword(password);

            regPage.setPrivacyPolicy();
            regPage.clickContinue();

            logger.info("Validate expected message");
            String confMsg = regPage.getConfirmationMsg();
            if(confMsg.contains("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test failed");
                logger.debug("Debug log");
                Assert.assertTrue(false);
            }
            Assert.assertEquals(confMsg, "Your Account Has Been Created!");
        }
        catch (Exception e){
            Assert.fail();
        }
        logger.info("***Finished TC001AccountRegistrationTest***");
    }
}
