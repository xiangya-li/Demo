package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/3 17:09
 * @Version :1.0
 */
public class BasePage {
    public static WebDriver driver;

    public WebElement findElement(By by,int timeout){
        if (timeout > 0){
            waitClickable(by, timeout);
        }
        return driver.findElement(by);
    }

    public WebElement findElement(By by){
        return driver.findElement(by);
    }



    public List<WebElement> findElements(By by,int timeout){
        if (timeout > 0){
            waitClickable(by, timeout);
        }
        return driver.findElements(by);
    }

    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public void quit(){
        driver.quit();
    }

    public void waitClickable(By by,int time){
        new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(by));


    }
}
