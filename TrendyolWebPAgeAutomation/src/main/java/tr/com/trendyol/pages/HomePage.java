package tr.com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tr.com.trendyol.utilities.Helper;
import org.testng.Reporter;

public class HomePage {

    private WebDriver driver ;
    private By accountButton = By.id("accountBtn");
    private By popupLoginMainMessage = By.className("text-center");
    private By inputEmail = By.id("email") ;
    private By inputPassword = By.id("password") ;
    private By loginSubmitButton = By.id("loginSubmit") ;
    private Helper helper ;
    private By wrongMailOrPasswordError = By.xpath("//div[@id='errorBox']");

    /**
     * HomePage is the page when the the browser first initiated.
     * @param driver:
     */

    public HomePage(WebDriver driver){
        this.driver = driver ;
        this.helper = new Helper(this.driver);
    }

    /**
     * Clicks the account button on the right top side of the page to initiate login process.
     */

    public void clickAccountButton(){
        helper.optional_wait(accountButton,3);
        try {
            driver.findElement(accountButton).click();
        }catch(org.openqa.selenium.StaleElementReferenceException e){
            driver.findElement(accountButton).click();
        }
        Reporter.log("* Clicked account button\n");

    }

    /**
     * Used to check if the user is on the Log in pop-up.
     * @return : Log pop-up Main Message.
     */

    public String getPopupLoginMainMessage(){
        helper.optional_wait(popupLoginMainMessage,3);
        return driver.findElement(popupLoginMainMessage).getText();
    }

    /**
     * Sets the mail address on the Log in pop up.
     * @param email : Mail address
     */

    public void setEmail(String email){
        helper.optional_wait(inputEmail,5);
        driver.findElement(inputEmail).sendKeys(email);
        Reporter.log("* Email address set\n");
    }

    /**
     * Sets the password on the Log in pop up.
     * @param password : Password
     */

    public void setPassword(String password){
        helper.optional_wait(inputPassword,5);
        driver.findElement(inputPassword).sendKeys(password);
        Reporter.log("* Password set\n");
    }

    /**
     * Clicks the submit Login Button on the log in pop up.
     * @return : LoggedInUserHomePage
     */

    public LoggedInUserHomePage clickLoginButton(){
        helper.optional_wait(loginSubmitButton,5);
        driver.findElement(loginSubmitButton).click();
        Reporter.log("* Clicked login button\n");
        return new LoggedInUserHomePage(driver);
    }

    public String getErrorMessageOnLogin(){
        return driver.findElement(wrongMailOrPasswordError).getText();

    }



}
