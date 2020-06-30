package pages.categorypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BoutiqueProductsPage;
import utilities.Helper;

import java.util.List;

/**
 * CategoryPage classes extends this BaseCategory class since they share same base behaviors.
 */

public class BaseCategory {

    private WebDriver driver ;
    private By boutique = new By.ByCssSelector(".component-item");

    public BaseCategory(WebDriver driver){
        this.driver =driver ;
    }

    /**
     * Goes to the ButiqueProductsPage after clicking.
     * @return ButiqueProductsPage
     */

    public BoutiqueProductsPage clickBoutique(){
        driver.findElements(boutique).get(2).click();
        return new BoutiqueProductsPage(driver);
    }











}
