package tr.com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {


    private By product = new By.ByCssSelector("div.image-container");
    private By productImages = new By.ByCssSelector("div.boutique-product div.image-container >img") ;
    private By productImages_2 = new By.ByCssSelector("div.p-card-img-wr >img");
    private By favButton = new By.ByCssSelector("div.fvrt-btn-wrppr i") ;
    private By freeShipping = new By.ByCssSelector("div.stmp-box-wrppr div.fc") ;

    private By productImageContainer = new By.ByCssSelector("div.p-card-img-wr >img") ;

    private By productBrand = new By.ByCssSelector("div.boutique-product div.description >span.brand") ;
    private By productName = new By.ByCssSelector("div.boutique-product div.description >span.name") ;
    private By productPriceWithDscnt = new By.ByCssSelector("div.price-box") ;
    private By productRatingCount = new By.ByCssSelector("div.boutique-product div.description >div.ratings span.ratingCount") ;
    private By productStar = new By.ByCssSelector("div.boutique-product div.description >div.ratings div >div.full") ;



    public ProductsPage(WebDriver driver){
        super(driver);
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

    public ProductDetailsPage clickProduct(int index){
        helper.waitVisibilityOfElement(product,5);
        WebElement element =driver.findElements(product).get(index);
        element.click();
        Reporter.log("* Clicked " + (index+1) + ". product on the boutique page.\n");
        return new ProductDetailsPage(driver);

    }

    /**
     * Checks if the user is on the BoutiqueProductPage
     * @return Boolean
     */

    public Boolean checkIfTheUserIs_OnBoutiqueProductPage(){
        helper.waitVisibilityOfElement(product,5);
        Boolean isPresent = driver.findElements(product).size() > 0;
        return isPresent ;

    }

    /**
     * Clicks the fav button on the right upside of the product image.
     * @param index of the fav button.(There are multiple fav buttons on the paga.)
     */

    public void clickFavButton(int index) {
       List<WebElement> elements = driver.findElements(favButton) ;
       elements.get(index).click();
    }

    public void getProductWithFreeShipping () { //Todo.
        List<WebElement> productImageContainers = driver.findElements(productImageContainer) ;
        List<String> prodcutName = new ArrayList<>() ;

    }

    private String getProductName (int index) {
        List<WebElement> productImageContainers = driver.findElements(productName) ;
        return productImageContainers.get(index).getText() ;
    }

    private String getProductPrice (int index) {
        List<WebElement> productPrices = driver.findElements(productPriceWithDscnt) ;
        return productPrices.get(index).getText();
    }

    private String getProductBrand (int index) {
        List<WebElement> productBrands = driver.findElements(productBrand) ;
        return productBrands.get(index).getText() ;
    }

    private String getRatingCount(int index) {
        List<WebElement> ratingCounts = driver.findElements(productRatingCount) ;
        return ratingCounts.get(index).getText() ;
    }

    private String getProductInfo(int index) {
        return getProductBrand(index) + "\n" +
                getProductName(index) + "\n" +
                getProductPrice(index) + "\n" +
                getRatingCount(index) + "\n" +
                getProductStar(index);
    }

    public void getWholePageProductInfo () {
        List <WebElement> products = driver.findElements(product) ;
        int size = products.size();
        for (int i = 0 ; i < size ; i++) {
            System.out.println(getProductInfo(i) +"\n");
            System.out.println("*****************");
        }
    }

    //TODO : consider products with 1,2,3 stars as well. And also learn how to get the stars one by one for each product instead of getting all of the stars on the page.
    private String getProductStar (int productIndex) {
        List<WebElement> allOfTheStarsOnThePage = driver.findElements(productStar) ;
        int indexOfLastStarOfProduct = ((productIndex+1)*5) - 1 ;
        String style = allOfTheStarsOnThePage.get(indexOfLastStarOfProduct).getAttribute("style") ;
        String percentage = style.substring(7,9) ;
        return "4" + "," +percentage ;



    }





}
