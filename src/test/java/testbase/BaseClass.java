package testbase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p= new Properties();
        p.load(file);

        logger= (Logger) LogManager.getLogger(this.getClass());
        switch (br.toLowerCase()){
            case "chrome": driver=new ChromeDriver(); break;
            case "edge": driver=new EdgeDriver(); break;
            case "firefox": driver=new FirefoxDriver(); break;
            default:
                System.out.println("Invalid browser name"); return;
        }
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(p.getProperty("appURL2")); //reading url from properties file
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    /*public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }
     */
    public String randomAlphaNumberic(){
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString+"@"+generatedNumber);
    }
}