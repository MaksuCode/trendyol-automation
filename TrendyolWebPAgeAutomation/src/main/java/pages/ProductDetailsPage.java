package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class ProductDetailsPage {

    private WebDriver driver ;
    private By addToCarButton = new By.ByCssSelector("div.add-to-bs-tx");
    private By basketItemCount = new By.ByCssSelector("div.basket-item-count >span");



    public ProductDetailsPage(WebDriver driver){
        this.driver = driver ;

    }

    public void addToCart(){
        driver.findElement(addToCarButton).click();
    }

    public Boolean checkIfTheUserIs_OnProductDetailsPage(){
        Helper helper = new Helper(driver);
        helper.optional_wait(addToCarButton,5);
        Boolean isPresent = driver.findElements(addToCarButton).size()> 0;
        return isPresent ;
    }

    public Boolean checkIfItem_AddedToCart(){
        Boolean isPresent = driver.findElements(basketItemCount).size() >0 ;
        return isPresent ;

    }

}
