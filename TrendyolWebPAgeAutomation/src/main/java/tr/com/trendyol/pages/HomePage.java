package tr.com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.trendyol.utilities.Constants;
import tr.com.trendyol.utilities.Helper;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private final WebDriver driver ;
    private final Helper helper ;
    private final By accountButton = By.id("accountBtn");
    private final By popupLoginMainMessage = By.className("text-center");
    private final By inputEmail = By.id("email") ;
    private final By inputPassword = By.id("password") ;
    private final By loginSubmitButton = By.id("loginSubmit") ;
    private final By wrongMailOrPasswordError = By.xpath("//div[@id='errorBox']");
    private final By modalCloseButton = By.className("modal-close");
    private final By userMailAddress = By.className("user-email");
    private final By category = By.className("category-header");
    private final By boutiqueImages = new By.ByCssSelector("span.image-container >img");
    private final By boutique = By.className("campaign") ;
    private final By subCategory = new By.ByCssSelector("div.category-box >a.sub-category-header") ;
    private final By subItem = new By.ByCssSelector("div.category-box > ul.sub-item-list a") ;   //>li >a

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

    public void clickLoginButton(){
        helper.optional_wait(loginSubmitButton,5);
        driver.findElement(loginSubmitButton).click();
        Reporter.log("* Clicked login button\n");
    }

    public String getErrorMessageOnLogin(){
        return driver.findElement(wrongMailOrPasswordError).getText();

    }

    public void clickBoutique() {
        driver.findElements(boutique).get(0).click();
    }

    /**
     * Closes the modal if appeared on the page.
     */

    public void closeModalIfExist() {
        boolean isPresent = driver.findElements(modalCloseButton).size() > 0;
        if (isPresent) {
            driver.findElement(modalCloseButton).click();
        }
        Reporter.log("* Modal window closed.\n");
    }

    /**
     * Used to check if the user successfully logged in by checking the user mail address under the account button.
     *
     * @return : User mail address.
     */

    public String getUserMailAddress_UnderAccountButton() {
        return helper.hoverOverElement_And_GetTheElementSearched(accountButton, userMailAddress);
    }

    /**
     * Clicks each of the categories, scrolls downs and checks if any boutique image is not downloaded properly.
     */

    public void processBoutiqueImages() { //TODO
        int sizeOfCategory = driver.findElements(category).size();
        for (int i = 0; i < sizeOfCategory; i++) {
            try {
                List<WebElement> webElementList = driver.findElements(category);
                webElementList.get(i).click();
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                List<WebElement> webElementList = driver.findElements(category);
                webElementList.get(i).click();
            }
            helper.scrollDown_until_no_new_image_appears(boutiqueImages);
            helper.getImagesNotDownloadedProperly(boutiqueImages);
            String nameOfCategory = driver.findElements(category).get(i).getText();
            System.out.println(nameOfCategory + " " + Constants.categoryHasFollowingBoutiques);
            helper.getUrlOfTheImages(boutiqueImages);
            helper.scrollUp();
        }
    }


    /**
     * Gets the names of categories on the page
     */

    private String[] getNamesOfCategories() {
        List<WebElement> categories = driver.findElements(category);
        String[] nameOfCategories = new String[categories.size()] ;
        for (int i = 0 ; i < categories.size() ; i++){
            nameOfCategories[i] = categories.get(i).getText() ;
        }
        return nameOfCategories;

    }

    /**
     * Clicks the category by the name.
     */

    public void clickCategoryByName(String categoryName){
        List<WebElement> categories = driver.findElements(category);
        String[] nameOfCategories = getNamesOfCategories();
        for (int i = 0 ; i < nameOfCategories.length ; i++){
            if (categoryName.equals(nameOfCategories[i])){
                categories.get(i).click();
            }
        }
    }

    /**
     * Clicks the category by the given index.(Starts from '0')
     */

    public void clickCategoryByIndex(int index) {
        List<WebElement> categories = driver.findElements(category);
        categories.get(index).click();
    }

    private void hoverOverCategories(int index)  {
        List<WebElement> categories = driver.findElements(category);
        Actions actions = new Actions(driver) ;
        actions.moveToElement(categories.get(index)).perform();

    }

    public void getSubCategoryNames(int index) {
            try {
                driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
                List<WebElement> subCategoryNames = driver.findElements(subCategory) ;
                    if (subCategoryNames.get(index).isDisplayed() && subCategoryNames.get(index).isEnabled()){
                        System.out.println(subCategoryNames.get(index).getText());
                    }
                System.out.println("##########################");
            }catch (Exception e){
                e.printStackTrace();
            }

    }

    public void getSubItemNames(){
        hoverOverCategories(1);
        try {
            driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
            List<WebElement> subItemList = driver.findElements(subItem) ;
            for (WebElement webElement : subItemList) {
                if (webElement.isEnabled() && webElement.isDisplayed())
                    System.out.println(webElement.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getAllCategoryInfo(){
        hoverOverCategories(1);
        List<WebElement> subCategoryNames = driver.findElements(subCategory) ;
        for (int i = 0 ; i < subCategoryNames.size() ; i++){
            getSubCategoryNames(i);
            getSubItemNames();

        }


        getSubItemNames();
    }








}
