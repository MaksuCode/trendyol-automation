package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import tr.com.trendyol.user.User;

public class SignInTest extends BaseTest {


    @Test
    public void successfulSignIn(){
        User user = new User(driver);
        user.goesTo(signInPage);
        user.setsMailAddress();
        user.setsPassword();
    }

    @Test
    public void signInWihtAnExistingAccount(){

    }

    @Test
    public void signInWithAnInvalidEmailAddress(){

    }

    @Test
    public void signInWithAnPasswordShorterThanSevenCharacters(){
        String password = signInPage.getPasswordShorterThanSevenCharacters();
        user.setsPassword(password);
    }

    @Test
    public void signInWithAnPasswordLongerThanFifteenCharacters(){
        String password = signInPage.getPasswordLongerThan15();
        user.setsPassword(password);
    }

    @Test
    public void signInWithAnPasswordWithoutDigits(){
        String password = signInPage.getPasswordWithoutDigits() ;
        user.setsPassword(password);
    }

    @Test
    public void signInWithAnPasswordWithoutLetters(){
        user.goesTo(signInPage);
        user.setsMailAddress();
        String password = signInPage.getPasswordWithoutLetter() ;
        user.setsPassword(password);

    }






}
