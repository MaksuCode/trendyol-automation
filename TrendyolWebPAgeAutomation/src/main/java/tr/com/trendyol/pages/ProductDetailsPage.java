package tr.com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import tr.com.trendyol.utilities.Helper;

public class ProductDetailsPage extends BasePage {

    private By addToCarButton = new By.ByCssSelector("div.add-to-bs-tx");
    private By basketItemCount = new By.ByCssSelector("div.basket-item-count >span");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    public void addToCart(){
        WebElement element = driver.findElement(addToCarButton) ;
        String text = element.getText() ;
        element.click();
        Reporter.log("* Clicked '" + text + "' on the page.\n");
    }

    public Boolean checkIfTheUserIs_OnProductDetailsPage(){
        Helper helper = new Helper(driver);
        helper.waitVisibilityOfElement(addToCarButton,5);
        Boolean isPresent = driver.findElements(addToCarButton).size()> 0;
        return isPresent ;
    }

    public Boolean checkIfItem_AddedToCart(){
        Boolean isPresent = driver.findElements(basketItemCount).size() >0 ;
        return isPresent ;

    }

}
