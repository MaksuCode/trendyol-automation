package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class BoutiqueProductsPage {

    private WebDriver driver;
    private By product = new By.ByCssSelector("div.image-container >img");

    public BoutiqueProductsPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     *
     */

    public void processProductImages(){
        Helper helper = new Helper(driver);
        helper.scrollDown_EndOfThePage();
        helper.getUrlOfTheImages(product);
        helper.getImagesNotDownloadedProperly(product);

    }

    /**
     * Goes to ProductDetailsPage by clicking a product.
     * @return : ProductDetailsPage
     */

    public ProductDetailsPage clickProduct(){
        driver.findElements(product).get(1).click();
        return new ProductDetailsPage(driver);

    }

    /**
     * Checks if the user is on the BoutiqueProductPage
     * @return Boolean
     */
    public Boolean checkIfTheUserIs_OnBoutiqueProductPage(){
        Boolean isPresent = driver.findElements(product).size() > 0;
        return isPresent ;

    }



}
