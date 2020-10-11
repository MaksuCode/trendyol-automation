package tr.com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class LogInPage extends BasePage {

    private String mailAddress ;

    public static final By email = By.id("login-email") ;
    private final By password = By.id("login-password-input") ;
    private final By loginSubmitButton = new By.ByCssSelector("button.q-primary.q-fluid") ;
    private final By loginErrorMessage = new By.ByCssSelector("div.lr-container span.message") ;
    private final By loginPageMessage = new By.ByCssSelector("div.lr-title >h3") ;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public LogInPage() {

    }

    /**
     * Gets the login page message after user logs in.
     */
    public String getLoginPageMessage(){
        helper.waitVisibilityOfElement(loginPageMessage,3);
        return driver.findElement(loginPageMessage).getText();
    }

    /**
     * Sets mail address on the Login page.
     */
    public void setEmailAdress(WebDriver driver , String mailAddress){
        WebElement element =driver.findElement(email) ;
        helper.waitVisibilityOfElement(element,5);
        if (element.getAttribute("value").length() >0){
            element.clear();
        }
        driver.findElement(email).sendKeys(mailAddress);
    }


    /**
     * Sets password on the Login page.
     */
    public void setPassword(WebDriver driver , String password){
        WebElement element =driver.findElement(this.password) ;
        helper.waitVisibilityOfElement(element,5);
        if (element.getAttribute("value").length() >0){
            element.clear();
        }
        element.sendKeys(password);
    }

    /**
     * Clicks the submit Login Button on the Login page.
     */
    public void clickLoginButton(WebDriver driver){
        helper.waitVisibilityOfElement(loginSubmitButton,5);
        driver.findElement(loginSubmitButton).click();
        Reporter.log("* Clicked login button\n");
    }

    /**
     * Gets the error message after clicking Login button
     */
    public String getErrorMessageOnLogin(){
        return driver.findElement(loginErrorMessage).getText();
    }

    public String setInvalidFormattedEmailAdress(){
        return "mustafakkcomosd" ;
    }

    public String setInvalidEmailAdress(){
        return "mustafaksu4545@gmail.com" ;
    }

    public String setInvalidPassword(){
        return "mustafa113";
    }

    public static By getEmail() {
        return email;
    }

    public By getPassword() {
        return password;
    }

}
