package testcases;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccount;
import testbase.BaseClass;
import utilities.DataProvider;

@Slf4j
public class TC003LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProvider.class)
    public void verifyLoginDDT(String email, String pwd, String exp) {
       logger.info("***Starting TC003 Login DDT");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            MyAccount mAcc = new MyAccount(driver);
            boolean targetPage = mAcc.isAccountPageExits();

            //Data is valid - login success - test pass - logout
            //Data is valid - login failed - test fail
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    mAcc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            //Data is invalid - login success - test fail - logout
            //Data is invalid - login failed - test pass
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    mAcc.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("***Finished TC003 Login DDT");
    }
}
