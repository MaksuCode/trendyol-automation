package tr.com.trendyol.pages.categorypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import tr.com.trendyol.pages.BoutiqueProductsPage;
import tr.com.trendyol.utilities.Helper;

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
        WebElement element = driver.findElements(boutique).get(1) ;
        String text = element.getText() ;
        element.click();
        Reporter.log("* Clicked " + text  + " boutique.\n");
        return new BoutiqueProductsPage(driver);
    }











}
