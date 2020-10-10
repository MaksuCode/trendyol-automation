package testcases;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tr.com.trendyol.utilities.Constants;

import java.util.List;


public class LoginTest extends BaseTest {

    private WebDriver driver;

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
            homePage.clickLoginButton();
            homePage.closeModalIfExist();
            homePage.clickBoutique();

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

    }


    @Test
    public void devTest(){
//        homePage.clickCategoryByName("ERKEK");
//        homePage.clickCategoryByIndex(4);
//        homePage.getSubCategoryNames();
        homePage.getAllCategoryInfo();
    }



}
