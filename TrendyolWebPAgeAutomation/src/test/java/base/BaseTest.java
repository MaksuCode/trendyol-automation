package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import tr.com.trendyol.pages.HomePage;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    private WebDriver driver ;
    protected HomePage homePage ;

    /**
     * @param browser in testng.xml is set to 3 different values for 3 browser.
     */

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser){
        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setCapability("marionette", true);
                cap.setBrowserName("firefox");
                driver = new FirefoxDriver();
                break;
            case "Explorer":
                System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goHome();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://www.trendyol.com");
    }

    @AfterClass
    public void shutDown(){
//        driver.quit();
    }


}




