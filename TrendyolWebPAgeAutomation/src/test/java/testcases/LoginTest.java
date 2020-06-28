package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoutiqueProductsPage;
import pages.LoggedInUserHomePage;
import pages.ProductDetailsPage;
import pages.categorypages.WomanCategoryPage;
import utilities.Constants;



public class LoginTest extends BaseTest {

    @Test
    public void successful_login_with_mail_and_purchase( )  {
        homePage.clickAccountButton();
        String popupLoginMessage =homePage.getPopupLoginMainMessage();
        Assert.assertEquals(popupLoginMessage,
                Constants.loginMainMessage,
                Constants.incorrectLoginMainMessage);
        homePage.setEmail(Constants.mailAddress);
        homePage.setPassword(Constants.password);
        LoggedInUserHomePage loggedInUserHomePage = homePage.clickLoginButton();
        loggedInUserHomePage.closeModalIfExist();
        Assert.assertEquals(loggedInUserHomePage.getUserMailAddress_UnderAccountButton(),
                Constants.mailAddress,
                Constants.incorrectMailAddress);
        loggedInUserHomePage.processBoutiqueImages();
        WomanCategoryPage womanCategoryPage = loggedInUserHomePage.clickWomanCategory();
        BoutiqueProductsPage boutiqueProductsPage = womanCategoryPage.clickBoutique();
        Assert.assertTrue(boutiqueProductsPage.checkIfTheUserIs_OnBoutiqueProductPage(),
                Constants.notOnTheBoutiqueProductPage);
        boutiqueProductsPage.processProductImages();
        ProductDetailsPage productDetailsPage =boutiqueProductsPage.clickProduct();
        Assert.assertTrue(productDetailsPage.checkIfTheUserIs_OnProductDetailsPage(),
                Constants.notOnTheProductDetailsPage);
        productDetailsPage.addToCart();
        Assert.assertTrue(productDetailsPage.checkIfItem_AddedToCart(),
                Constants.productNotAddedToTheCart);

    }


    /**
     * New Cases could be like these ones...
     */

    @Test
    public void fail_login_with_wrong_password()  {

    }

    @Test
    public void fail_login_with_wrong_mail(){

    }


}
