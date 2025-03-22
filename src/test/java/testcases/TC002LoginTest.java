package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccount;
import testbase.BaseClass;

public class TC002LoginTest extends BaseClass {

    @Test
    public void verifyLogin(){
        logger.info("***Starting TC002 Login Test***");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            MyAccount mAcc = new MyAccount(driver);
            boolean targetPage = mAcc.isAccountPageExits();
            Assert.assertEquals(targetPage, true, "Login Failed");
            //Assert.assertTrue(targetPage);
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("***Finish TC002 Login Test***");

    }
}
