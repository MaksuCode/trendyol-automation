package testcases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tr.com.trendyol.pages.BoutiqueProductsPage;
import tr.com.trendyol.pages.LoggedInUserHomePage;
import tr.com.trendyol.pages.ProductDetailsPage;
import tr.com.trendyol.pages.categorypages.WomanCategoryPage;
import tr.com.trendyol.utilities.Constants;

import java.util.List;


public class LoginTest extends BaseTest {

    private WebDriver driver;
    private By productImageContainer = new By.ByCssSelector("div.p-card-img-wr >img") ;




    @Parameters({"emailAdress" , "password"})
    @Test(timeOut = 100000)
    public void successful_login (String emailAdress , String password )  {
            homePage.clickAccountButton();
            String popupLoginMessage =homePage.getPopupLoginMainMessage();
            Assert.assertEquals(popupLoginMessage,
                    Constants.loginMainMessage,
                    Constants.incorrectLoginMainMessage);
            homePage.setEmail(emailAdress);
            homePage.setPassword(password);
            LoggedInUserHomePage loggedInUserHomePage = homePage.clickLoginButton();
            loggedInUserHomePage.closeModalIfExist();
            Assert.assertEquals(loggedInUserHomePage.getUserMailAddress_UnderAccountButton(),
                    emailAdress,
                    password);
//            loggedInUserHomePage.processBoutiqueImages();
            WomanCategoryPage womanCategoryPage = loggedInUserHomePage.clickWomanCategory();
            BoutiqueProductsPage boutiqueProductsPage = womanCategoryPage.clickBoutique();
            Assert.assertTrue(boutiqueProductsPage.checkIfTheUserIs_OnBoutiqueProductPage(),
                    Constants.notOnTheBoutiqueProductPage);

//            boutiqueProductsPage.processProductImages();
//            ProductDetailsPage productDetailsPage =boutiqueProductsPage.clickProduct(2);
//            Assert.assertTrue(productDetailsPage.checkIfTheUserIs_OnProductDetailsPage(),
//                    Constants.notOnTheProductDetailsPage);
//            productDetailsPage.addToCart();
//            Assert.assertTrue(productDetailsPage.checkIfItem_AddedToCart(),
//                    Constants.productNotAddedToTheCart);
    }

    /**
     * New Cases could be like these ones...
     */

    @Parameters({"emailAdress" , "password"})
    @Test
    public void fail_login (String emailAdress , String password)  {
        homePage.clickAccountButton();
        String popupLoginMessage =homePage.getPopupLoginMainMessage();
        Assert.assertEquals(popupLoginMessage,
                Constants.loginMainMessage,
                Constants.incorrectLoginMainMessage);
        homePage.setEmail(emailAdress);
        homePage.setPassword(password);
        homePage.clickLoginButton();
        Assert.assertEquals(homePage.getErrorMessageOnLogin(),Constants.wrongMailOrPasswordError);
        Reporter.log("test log");

    }

    @Parameters({"emailAdress" , "password"})
    @Test
    public void productNameTest(String emailAdress , String password) {
        homePage.clickAccountButton();
        String popupLoginMessage =homePage.getPopupLoginMainMessage();
        Assert.assertEquals(popupLoginMessage,
                Constants.loginMainMessage,
                Constants.incorrectLoginMainMessage);
        homePage.setEmail(emailAdress);
        homePage.setPassword(password);
        LoggedInUserHomePage loggedInUserHomePage = homePage.clickLoginButton();
        loggedInUserHomePage.closeModalIfExist();
        Assert.assertEquals(loggedInUserHomePage.getUserMailAddress_UnderAccountButton(),
                emailAdress,
                password);
//            loggedInUserHomePage.processBoutiqueImages();
        WomanCategoryPage womanCategoryPage = loggedInUserHomePage.clickWomanCategory();
        BoutiqueProductsPage boutiqueProductsPage = womanCategoryPage.clickBoutique();
//        System.out.println(boutiqueProductsPage.getProductInfo(1));
        boutiqueProductsPage.getWholePageProductInfo();



//        WebElement element = driver.findElement(productImageContainer) ;
//
////        List<WebElement> productImageContainers = driver.findElements(productImageContainer) ;
//        System.out.println(element.getAttribute("alt"));












    }



}
