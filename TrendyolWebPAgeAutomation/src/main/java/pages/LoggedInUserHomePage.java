package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.categorypages.*;
import utilities.Constants;
import utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * LoggedInUserHomePage is the page after user logged in successfully.
 */

public class LoggedInUserHomePage {

    private WebDriver driver;
    private By modalCloseButton = By.className("modal-close");
    private By accountButton = By.id("accountBtn");
    private By userMailAddress = By.className("user-email");
    private By category = By.className("category-header");
    private By boutiqueImages = new By.ByCssSelector("span.image-container >img");
//    private By subCategory = new By.ByCssSelector("div.category-box >a") ;  //ul[@class='sub-item-list']//li//a
    private By subCategory = By.xpath("//div[@class='category-box']//a[@class='sub-category-header']") ;
    private Helper helper;


    public LoggedInUserHomePage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(this.driver);
    }

    /**
     * Closes the modal if appeared on the page.
     */

    public void closeModalIfExist() {
        boolean isPresent = driver.findElements(modalCloseButton).size() > 0;
        if (isPresent) {
            driver.findElement(modalCloseButton).click();
        }
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

    public void processBoutiqueImages() {
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

    private void getNamesOfCategories() {
        List<WebElement> categories = driver.findElements(category);
        for (WebElement name : categories) {
            name.getText();
        }
    }


    public void getSubCategories() { //TODO
//        List<WebElement> categories = driver.findElements(category);
//        for (WebElement elements : categories){
//            helper.hoverOverElement_And_GetTheElementListSearched(elements , subCategory);
//        }
//    }
    }

    /**
     * Below , there are click methods that go to the corresponding CategoryPages.
     * The reason why I did like this is I wanted to keep following POM(Page Object Model) which enables us to hold the pages as Objects.
     *
     * @return : Category pages (individually)
     */

    public WomanCategoryPage clickWomanCategory() {
        driver.findElements(category).get(0).click();
        return new WomanCategoryPage(driver);
    }

    public MenCategoryPage clickMenCategory() {
        driver.findElements(category).get(1).click();
        return new MenCategoryPage(driver);
    }

    public KidCategoryPage clickKidCategory() {
        driver.findElements(category).get(2).click();
        return new KidCategoryPage(driver);
    }

    public HouseCategoryPage clickHouseCategory() {
        driver.findElements(category).get(3).click();
        return new HouseCategoryPage(driver);
    }


    public SupermarketCategoryPage clickSupermarketCategory() {
        driver.findElements(category).get(4).click();
        return new SupermarketCategoryPage(driver);
    }


    public CosmeticCategoryPage clickCosmeticCategory() {
        driver.findElements(category).get(5).click();
        return new CosmeticCategoryPage(driver);
    }

    public ShoesAndBagCategoryPage clickShoesAndBagCategory() {
        driver.findElements(category).get(6).click();
        return new ShoesAndBagCategoryPage(driver);
    }

    public WatchAndAccessoriesCategoryPage clickWatchAndAccessoriesCategory() {
        driver.findElements(category).get(7).click();
        return new WatchAndAccessoriesCategoryPage(driver);
    }

    public ElectronicCategoryPage clickElectronicCategory() {
        driver.findElements(category).get(8).click();
        return new ElectronicCategoryPage(driver);
    }


}





