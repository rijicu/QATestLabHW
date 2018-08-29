import com.sun.xml.internal.ws.encoding.MtomCodec;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.EventHandler;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by stoykov on 29.08.2018.
 */
public class QATestLabHW3a {

    public static void main(String[] args) throws InterruptedException {
        String userAdminEmail = "webinar.test@gmail.com";
        String userAdminPass = "Xcg7299bnSmMuRLp9ITw";

        WebDriver driver = getChromeDriver();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new WebEventListener());

        eventDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        eventDriver.manage().window().maximize();
        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        eventDriver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement emailInput = eventDriver.findElement(By.id("email"));
        WebElement passInput = eventDriver.findElement(By.id("passwd"));
        WebElement submitButton = eventDriver.findElement(By.name("submitLogin"));

        //Login as admin
        emailInput.clear();
        emailInput.sendKeys(userAdminEmail);
        passInput.clear();
        passInput.sendKeys(userAdminPass);
        submitButton.click();
        //open Catalog - Category
        WebElement adminCatalog = eventDriver.findElement(By.id("subtab-AdminCatalog"));
        builder.moveToElement(adminCatalog).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("subtab-AdminCategories"))).click();
        //add new category
        WebElement addCategoryButton = eventDriver.findElement(By.xpath("//a[@title='Добавить категорию']"));
        addCategoryButton.click();
        WebElement inputCategoryName = eventDriver.findElement(By.name("name_1"));
        inputCategoryName.clear();
        inputCategoryName.sendKeys("TestCategory");
        WebElement saveButton = eventDriver.findElement(By.id("category_form_submit_btn"));
        saveButton.click();
        //check category is created
        String alertMessage = eventDriver.findElement(By.className("alert-success")).getText();
        //System.out.println(alertMessage);
        if (alertMessage.contains("Создано")){
            System.out.println("Category created!");
        }else{
            System.out.println("Category not created!");
        }
        //find created category
        WebElement inputNameFilter = eventDriver.findElement(By.name("categoryFilter_name"));
        inputNameFilter.clear();
        inputNameFilter.sendKeys("TestCategory");
        WebElement searchButton = eventDriver.findElement(By.id("submitFilterButtoncategory"));
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(eventDriver.findElement(By.xpath("//td[contains(text(),'TestCategory')]"))));
        //exit
        WebElement userBox = eventDriver.findElement(By.id("header_employee_box"));
        userBox.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("header_logout"))).click();

        eventDriver.quit();

    }

    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", QATestLabHW3.class.getResource("chromedriver.exe").getPath());
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
