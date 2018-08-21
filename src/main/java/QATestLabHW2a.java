import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by stoykov on 14.08.2018.
 */
public class QATestLabHW2a {



    public static void main(String[] args) throws InterruptedException {
        String userAdminEmail = "webinar.test@gmail.com";
        String userAdminPass = "Xcg7299bnSmMuRLp9ITw";

        WebDriver driver = getChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passInput = driver.findElement(By.id("passwd"));
        WebElement submitButton = driver.findElement(By.name("submitLogin"));


        emailInput.clear();
        emailInput.sendKeys(userAdminEmail);
        passInput.clear();
        passInput.sendKeys(userAdminPass);
        submitButton.click();
        waitElementToLoad(2000);

        WebElement userBox = driver.findElement(By.id("header_employee_box"));
        userBox.click();
        waitElementToLoad(2000);

        WebElement exitButton = driver.findElement(By.id("header_logout"));
        exitButton.click();


        driver.quit();

    }



    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", QATestLabHW2a.class.getResource("chromedriver.exe").getPath());
        return new ChromeDriver();
    }

    public static WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", QATestLabHW2a.class.getResource("geckodriver.exe").getPath());
        return new FirefoxDriver();
    }

    public static void waitElementToLoad(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

}
