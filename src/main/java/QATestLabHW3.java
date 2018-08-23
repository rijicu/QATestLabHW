import com.sun.xml.internal.ws.encoding.MtomCodec;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by stoykov on 14.08.2018.
 */
public class QATestLabHW3 {



    public static void main(String[] args) throws InterruptedException {
        String userAdminEmail = "webinar.test@gmail.com";
        String userAdminPass = "Xcg7299bnSmMuRLp9ITw";

        WebDriver driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passInput = driver.findElement(By.id("passwd"));
        WebElement submitButton = driver.findElement(By.name("submitLogin"));


        emailInput.clear();
        emailInput.sendKeys(userAdminEmail);
        passInput.clear();
        passInput.sendKeys(userAdminPass);
        submitButton.click();
        //waitElementToLoad(2000);

        WebElement adminCatalog = driver.findElement(By.id("subtab-AdminCatalog"));
        builder.moveToElement(adminCatalog).build().perform();
        //waitElementToLoad(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("subtab-AdminCategories"))).click();

        WebElement addCategoryButton = driver.findElement(By.xpath("//a[@title='Добавить категорию']"));
        addCategoryButton.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Добавить категорию']"))).click();

        WebElement inputCategoryName = driver.findElement(By.name("name_1"));
        inputCategoryName.clear();
        inputCategoryName.sendKeys("TestCategory");

        WebElement saveButton = driver.findElement(By.id("category_form_submit_btn"));
        saveButton.click();

        String alertMessage = driver.findElement(By.className("alert-success")).getText();
        System.out.println(alertMessage);
        if (alertMessage.contains("Создано")){
            System.out.println("Category created!");
        }else{
            System.out.println("Category not created!");
        }

        //System.out.println(driver.findElement(By.className("alert-success")).getText());

        //wait.until(ExpectedConditions.textToBe(By.className("alert-success"),"Создано"));

        waitElementToLoad(3000);

/*
        WebElement userBox = driver.findElement(By.id("header_employee_box"));
        userBox.click();
        waitElementToLoad(2000);

        WebElement exitButton = driver.findElement(By.id("header_logout"));
        exitButton.click();
*/


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
