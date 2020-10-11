package tr.com.trendyol.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Helper class is a helper class which contains some actions to be implemented over page elements such as hovering over a image.
 * New methods can be added here according to project requirements.
 * Creating an apart helper class improves code readability and maintenance.
 */
public class Helper {

    public WebDriver driver ;
    private By androidOnThePageEnd = By.linkText("Android");



    public Helper(WebDriver driver)  {
        this.driver = driver ;
    }

    /**
     * Helps to find an element which is appeared after hovering over another element.
     * @param elementHovered : Element hovered over
     * @param elementSearched :Element appeared on the page after hovering over the "elementHovered" element.
     * @return : Text of the Element searched.
     */

    public String hoverOverElement_And_GetTheElementSearched(By elementHovered, By elementSearched){
        WebElement webElement = driver.findElement(elementHovered);
        Actions actions = new Actions(driver) ;
        actions.moveToElement(webElement).perform();
        WebDriverWait wait = new WebDriverWait(driver,5) ;
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementSearched)));
        return driver.findElement(elementSearched).getText();

    }

    /**
     * Scrolls downs until the given elemet on the page.
     * @param element : Element to which is going to  be scrolled.
     */

    public void scrollDownThroughImages_ToTheElementGiven(WebElement element){
        ((JavascriptExecutor)driver).executeScript(JsScripts.scroll_down_into_view_script,element);
        WebDriverWait wait = new WebDriverWait(driver , 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.withTimeout(Duration.ofSeconds(5));
    }

    /**
     * Scrolls down to the end of the page. I used it  to get the elements through all of the page.
     */

    public void scrollDown_EndOfThePage(){
        WebElement element = driver.findElement(androidOnThePageEnd);
        ((JavascriptExecutor)driver).executeScript(JsScripts.scroll_down_into_view_script,element);
        WebDriverWait wait = new WebDriverWait(driver , 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.withTimeout(Duration.ofSeconds(5));
    }

    /**
     * Scrolls down to the end of the page until no new images appears. Used it to get ALL of the desired elements on the page.
     * @param elementToBeScrolledThrough : Elements to be scrolled through page.
     */

    public void scrollDown_until_no_new_image_appears(By elementToBeScrolledThrough){
        int numberOfImagesFirst = getNumberOf_GivenElement(elementToBeScrolledThrough) ;
        while (true){
            ((JavascriptExecutor)driver).executeScript(JsScripts.scroll_down_script,"");
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.pollingEvery(6, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
            ((JavascriptExecutor)driver).executeScript(JsScripts.scroll_down_script,"");
            if (getNumberOf_GivenElement(elementToBeScrolledThrough)==numberOfImagesFirst){
                break;
            }
        }
        System.out.println(getNumberOf_GivenElement(elementToBeScrolledThrough)+ " elements found...");
    }


    /**
     * Gets the images not downloaded properly.
     * @param imageSearchedThroughPage : Images that are searched to be checked if  downloaded or not.
     * The reason why I make it with a parameter is to be able to use it for both boutique images and product images.
     */

    public void getImagesNotDownloadedProperly(By imageSearchedThroughPage) {
        List<WebElement> imageList = driver.findElements(imageSearchedThroughPage);
        waitVisibilityOfElement(imageSearchedThroughPage,5);
        for (WebElement image : imageList){
            boolean loaded = (boolean) ((JavascriptExecutor)driver).executeScript(
                    JsScripts.imageDownloaded, image);
            if (!loaded) {
                System.out.println(image+ image.getText()+ Constants.imageNotDownloadedMessage);
            }
        }
    }

    /**
     * Scrolls to the top of the page.
     */
    public void scrollUp(){
        ((JavascriptExecutor)driver).executeScript(JsScripts.scroll_up_script);
    }

    /**
     * Gets the names of the given element present on the page.
     * @return String names
     */
    public String getNamesOfTheElement(By element){
        List<WebElement> elements = driver.findElements(element);
        for (WebElement name : elements){
            System.out.println(name.getText());
        }
        return null;
    }

    public String getNamesOfTheElement(List<WebElement> elements){
        for (WebElement name : elements){
            System.out.println(name.getText());
        }
        return null;
    }

    /**
     * @return : Numbers of the given element on the page.
     */

    public int getNumberOf_GivenElement(By element){
        return driver.findElements(element).size();
    }


    /**
     * Prints the urls of images found of the given element on the page.
     * @param element :  By
     */
    public void getUrlOfTheImages(By element){
        List<WebElement> elements = driver.findElements(element) ;
        for (WebElement url : elements){
            System.out.println(url.getAttribute("src"));
        }
    }

    /**
     * Waits for the visibility of the given element
     * @param waitedElementLocator :By  element to be waited to appear
     * @param seconds : time to wait in seconds.
     */

    public void waitVisibilityOfElement(By waitedElementLocator , int seconds){
        WebElement elementToBeWaited = driver.findElement(waitedElementLocator);
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(elementToBeWaited));
        }catch(org.openqa.selenium.StaleElementReferenceException e){
            wait.until(ExpectedConditions.visibilityOf(elementToBeWaited));
        }
    }




    /**
     * Waits for the visibility of the given element
     * @param elementToBeWaited : Webelement element to be waited to appear
     * @param seconds time to wait in seconds.
     */


    public void waitVisibilityOfElement(WebElement elementToBeWaited , int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.visibilityOf(elementToBeWaited));

    }










}
