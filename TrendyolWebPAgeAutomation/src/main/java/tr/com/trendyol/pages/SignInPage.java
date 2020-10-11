package tr.com.trendyol.pages;

import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver){
        super(driver);
    }

    public SignInPage() {
    }

    public String getPasswordWithoutLetter() {
        return "1234567";
    }

    public String getPasswordWithoutDigits() {
        return "abcdefgh";
    }

    public String getPasswordLongerThan15() {
        return "abcdef12344444";
    }

    public String getPasswordShorterThanSevenCharacters(){
        return "abcd12" ;
    }
}
