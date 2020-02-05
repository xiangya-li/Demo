package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/3 17:08
 * @Version :1.0
 */
public class App extends BasePage {

    ContactPage ContactPage;
    BroadCastPage BroadCastPage;

    /*
            todo：添加getCookies方法，用于LoginWithCookies方法的wwrtx.sid获取
     */

    public App LoginWithCookies(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("pageLoadStrategy","none");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/");
        findElement(By.linkText("企业登录")).click();
        driver.manage().addCookie(new Cookie("wwrtx.sid","sTfrnUROqQqoPAxOgKZajySMY_8X27w2PMNa4k9XhNbRIOiUl2HQpNH-pjR43xvZ","/",null));
        driver.navigate().refresh();
        return this;
    }

    public ContactPage toContact(){
        findElement(By.xpath("//span[contains(.,'通讯录')]")).click();
        return ContactPage;
    }

    public ContactPage toMember(){
        findElement(By.linkText("添加成员"),5).click();
        return ContactPage;
    }

    public BroadCastPage toManageToolsPage(){
        findElement(By.xpath("//span[contains(.,'管理工具')]"),2).click();
        return BroadCastPage;
    }
}
