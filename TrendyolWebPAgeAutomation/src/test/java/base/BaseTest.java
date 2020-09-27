package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import tr.com.trendyol.pages.BoutiqueProductsPage;
import tr.com.trendyol.pages.HomePage;
import tr.com.trendyol.pages.LoggedInUserHomePage;
import tr.com.trendyol.pages.categorypages.WomanCategoryPage;

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
        driver.get("https://www.trendyol.com/tum--urunler?q=ayakkab%C4%B1&qt=ayakkab%C4%B1&st=ayakkab%C4%B1&pi=3"); //"https://trendyol.com/
    } //https://www.trendyol.com/tum--urunler?q=ayakkab%C4%B1&qt=ayakkab%C4%B1&st=ayakkab%C4%B1&pi=3

    @AfterClass
    public void shutDown(){
        driver.quit();
    }


}




