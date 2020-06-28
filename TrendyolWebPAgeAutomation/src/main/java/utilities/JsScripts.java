package utilities;

/**
 * This class just holds the JS Scripts to be used inside JavascriptExecutor class.
 * Keeping these scripts apart improve code readability.
 * New scripts can be added here as needed.
 */
public class JsScripts {

    public static String scroll_up_script = "window.scrollTo(0, 0)" ;
    public static String scroll_down_script = "arguments[0].scrollIntoView(true)" ;
    public static String imageDownloaded = "return arguments[0].complete && typeof arguments[0].naturalWidth != undefined && arguments[0].naturalWidth > 0" ;
    public static String downloadCompleteImage = "return arguments[0].complete";


}
