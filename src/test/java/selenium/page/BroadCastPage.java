package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/4 19:53
 * @Version :1.0
 */
public class BroadCastPage extends BasePage {
    public BroadCastPage messageSend(String title,String content,String summary,String author){
//        点击消息群发
        findElement(By.xpath("//a[contains(@href, '#createMessage')]"),2).click();
        findElement(By.linkText("选择需要发消息的应用"),2).click();
//        选择应用
        findElement(By.cssSelector(".ww_icon_AppNotice")).click();
        findElement(By.linkText("确定")).click();
//        选择范围
        findElement(By.linkText("选择发送范围")).click();
//       定位到搜索框,涉及到Page Load知识，动态加载
        findElement(By.id("memberSearchInput")).sendKeys("李向雅");
        findElement(By.cssSelector(".ww_searchResult_title_peopleDepartment")).click();
        findElement(By.linkText("确认")).click();
//        定位到标题
        findElement(By.cssSelector(".ww_editorTitle")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
//        定位到正文，切换至frame
        System.out.println(driver.getWindowHandles());
        driver.switchTo().frame(0);
        findElement(By.cssSelector(".msg_noticeEditor_frameBody")).sendKeys(content);
//        从frame0切换回来
        driver.switchTo().defaultContent();
//        滚动至指定位置
//        String js = "document.documentElement.scrollTop=1200";
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1200)");
//        点击定位
        findElement(By.cssSelector(".msg_edit_infoItem_textWord")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);
//        定位作者
        findElement(By.cssSelector(".js_amrd_sendName")).sendKeys(author);
        findElement(By.linkText("发送")).click();
        findElement(By.linkText("确定")).click();
        return this;
    }

    public List<String> getListMsg(){
        findElement(By.linkText("已发送")).click();
        List<String> msg = new ArrayList<>();
        findElements(By.cssSelector(".msg_history_msgList_td"),5).forEach(element -> {
            msg.add(element.getText());
        } );
        return msg;
    }
}
