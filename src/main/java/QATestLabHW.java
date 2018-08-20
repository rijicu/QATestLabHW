import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by stoykov on 14.08.2018.
 */
public class QATestLabHW {
    public static void main(String[] args) {
        WebDriver driver = getChromeDriver();
        driver.manage().window().maximize();


        driver.get("https://www.bing.com/");

    }



    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", QATestLabHW.class.getResource("chromedriver.exe").getPath());
        return new ChromeDriver();
    }

    public static WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", QATestLabHW.class.getResource("geckodriver.exe").getPath());
        return new FirefoxDriver();
    }
}
