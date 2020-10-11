package tr.com.trendyol.pages;

import org.openqa.selenium.WebDriver;
import tr.com.trendyol.utilities.Helper;

public class BasePage {

    protected WebDriver driver ;
    protected Helper helper ;

    public BasePage(WebDriver driver){
        this.driver = driver ;
        this.helper = new Helper(driver);
    }


    public BasePage() {

    }
}
