package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import tr.com.trendyol.pages.HomePage;
import tr.com.trendyol.pages.LogInPage;
import tr.com.trendyol.pages.SignInPage;
import tr.com.trendyol.user.User;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    protected WebDriver driver ;
    protected HomePage homePage ;
    protected LogInPage logInPage = new LogInPage() ;
    protected SignInPage signInPage = new SignInPage() ;
    protected User user ;

    /**
     * @param browser in testng.xml is set to 3 different values for 3 browser.
     */

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser){
        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                this.driver = new ChromeDriver();
                break;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setCapability("marionette", true);
                cap.setBrowserName("firefox");
                this.driver = new FirefoxDriver();
                break;
            case "Explorer":
                System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
                this.driver = new InternetExplorerDriver();
                break;
        }
        launchHome();
        user = new User(driver) ;
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }


    public void launchHome(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.trendyol.com");
        homePage = new HomePage(driver);
        homePage.closeFancyBoxIfExist();
    }
}




