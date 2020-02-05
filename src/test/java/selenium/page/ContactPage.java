package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/3 17:11
 * @Version :1.0
 */
public class ContactPage extends BasePage {

    public ContactPage add(String name,String id,String phone){
        waitClickable(By.id("username"),5);
        findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("memberAdd_acctid")).sendKeys(id);
        findElement(By.id("memberAdd_phone")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public ContactPage delete(String name) throws InterruptedException {
        waitClickable(By.id("memberSearchInput"),5);
//        清除搜索输入框
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(name);
        Thread.sleep(5000);
        findElement(By.linkText("删除")).click();
        waitClickable(By.linkText("确认"),5);
        findElement(By.linkText("确认")).click();
        findElement(By.id("memberSearchInput")).click();
        return this;

    }

    public ContactPage deleteContactPage(){
        waitClickable(By.cssSelector(".ww_checkbox"),5);
        List<WebElement> elements = findElements(By.cssSelector(".ww_checkbox"));
        for (int i = 1; i < elements.size() ; i++) {
            elements.get(i).click();
            {
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        findElement(By.linkText("删除"),0).click();
        findElement(By.linkText("确认"),5).click();
        return this;
    }

    public ContactPage importFromFile(String path){
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(By.partialLinkText("批量导入/导出"),0).click();
        findElement(By.linkText("文件导入"),2).click();
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(By.id("js_upload_file_input")).sendKeys(path);
        findElement(By.id("submit_csv")).click();
        return this;
    }

}
