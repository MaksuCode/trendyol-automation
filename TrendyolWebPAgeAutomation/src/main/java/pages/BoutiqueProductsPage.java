package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class BoutiqueProductsPage {


    private WebDriver driver;
    private By product = new By.ByCssSelector("div.image-container");
    private By productImages = new By.ByCssSelector("div.boutique-product div.image-container >img") ;
    private By productImages_2 = new By.ByCssSelector("div.p-card-img-wr >img");
    private Helper helper ;




    public BoutiqueProductsPage(WebDriver driver){
        this.driver = driver;
        this.helper = new Helper(this.driver) ;
    }

    /**
     *Processing product images on the Boutique categories.
     */

    public void processProductImages(){

        helper.scrollDown_until_no_new_image_appears(product);
        try{
            driver.findElements(productImages) ;
            helper.getUrlOfTheImages(productImages);
            helper.getImagesNotDownloadedProperly(productImages);
        }
        catch (Exception ex)
        {
            driver.findElements(productImages_2);
            helper.getUrlOfTheImages(productImages_2);
            helper.getImagesNotDownloadedProperly(productImages_2);
        }
        helper.scrollUp();

    }

    /**
     * Goes to ProductDetailsPage by clicking a product.
     * @return : ProductDetailsPage
     */

    public ProductDetailsPage clickProduct(){
        helper.optional_wait(product,5);
        driver.findElements(product).get(1).click();
        return new ProductDetailsPage(driver);

    }

    /**
     * Checks if the user is on the BoutiqueProductPage
     * @return Boolean
     */

    public Boolean checkIfTheUserIs_OnBoutiqueProductPage(){
        helper.optional_wait(product,5);
        Boolean isPresent = driver.findElements(product).size() > 0;
        return isPresent ;

    }

}
