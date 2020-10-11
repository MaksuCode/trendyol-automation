package tr.com.trendyol.user;

import org.openqa.selenium.WebDriver;
import tr.com.trendyol.pages.HomePage;
import tr.com.trendyol.pages.LogInPage;
import tr.com.trendyol.pages.BasePage;

public class User {

    private final WebDriver driver ;
    private String mailAddress = "mustafaksu4@gmail.com";
    private String password = "mustafa112" ;

    public User(WebDriver driver) {
        this.driver = driver;
    }


    public void goesTo (BasePage basePage) {
        String url = null;
        if (basePage.getClass().equals(LogInPage.class)){
            url = "https://www.trendyol.com/giris";
        }else if (basePage.getClass().equals(HomePage.class)){
            url = "https://www.trendyol.com/" ;
        }
        driver.navigate().to(url);
    }

    public void setsMailAddress(){
        LogInPage logInPage = new LogInPage(driver);
        logInPage.setEmailAdress(driver , mailAddress);
    }

    public void setsMailAddress(String mailAddress){
        LogInPage logInPage = new LogInPage(driver);
        logInPage.setEmailAdress(driver , mailAddress);
    }

    public void setsPassword(){
        String password = this.password;
        LogInPage logInPage = new LogInPage(driver);
        logInPage.setPassword(driver , password);
    }

    public void setsPassword(String password){
        LogInPage logInPage = new LogInPage(driver);
        logInPage.setPassword(driver , password);
    }

    public void clicksLoginButton(){
        LogInPage logInPage = new LogInPage(driver);
        logInPage.clickLoginButton(driver);
    }

    public void closesModalIfExist() {
        HomePage homePage = new HomePage(driver) ;
        homePage.closeModalIfExist(driver);
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
