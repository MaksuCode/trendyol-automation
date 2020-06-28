package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver ;
    private By accountButton = By.id("accountBtn");
    private By popupLoginMainMessage = By.className("text-center");
    private By inputEmail = By.id("email") ;
    private By inputPassword = By.id("password") ;
    private By loginSubmitButton = By.id("loginSubmit") ;

    /**
     * HomePage is the page when the the browser first initiated.
     * @param driver:
     */

    public HomePage(WebDriver driver){
        this.driver = driver ;
    }

    /**
     * Clicks the account button on the right top side of the page to initiate login process.
     */
    public void clickAccountButton(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(accountButton));
        try {
            driver.findElement(accountButton).click();
        }catch(org.openqa.selenium.StaleElementReferenceException e){
            driver.findElement(accountButton).click();
        }
    }

    /**
     * Used to check if the user is on the Log in pop-up.
     * @return : Log pop-up Main Message.
     */

    public String  getPopupLoginMainMessage(){
        return driver.findElement(popupLoginMainMessage).getText();
    }

    /**
     * Sets the mail address on the Log in pop up.
     * @param email : Mail address
     */

    public void setEmail(String email){ //TODO :bekleme koy
        driver.findElement(inputEmail).sendKeys(email);
    }

    /**
     * Sets the password on the Log in pop up.
     * @param password : Password
     */

    public void setPassword(String password){
        driver.findElement(inputPassword).sendKeys(password);
    }

    /**
     * Clicks the submit Login Button on the log in pop up.
     * @return : LoggedInUserHomePage
     */

    public LoggedInUserHomePage clickLoginButton(){
        driver.findElement(loginSubmitButton).click();
        return new LoggedInUserHomePage(driver);
    }

}
