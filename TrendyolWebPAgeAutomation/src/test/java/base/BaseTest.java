package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pages.HomePage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver driver ;
    protected HomePage homePage ;

    /**
     * Log process before every test.
     * @throws FileNotFoundException :
     */
    @BeforeTest
    public void firstSetUp() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new File("log.txt")) ;
        System.setOut(printStream);
    }


    @BeforeClass
    public void setUp(){
        String browser ="chrome";
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe") ;
            driver = new ChromeDriver();
        }
        else if (browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe") ;
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setCapability("marionette", true);
            cap.setBrowserName("firefox");
            driver = new FirefoxDriver();
        }
        else if (browser.equals("explorer")){
            System.setProperty("webdriver.ie.driver","drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goHome();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://trendyol.com/");
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }


}




