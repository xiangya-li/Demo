package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;
import selenium.page.BroadCastPage;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/4 20:27
 * @Version :1.0
 */
public class TestMessageGroup {
    public static App app;
    public static BroadCastPage broadCastPage;

    @BeforeClass
    public static void beforeAll(){
        app = new App();
        broadCastPage = new BroadCastPage();
        app.LoginWithCookies();
    }

    @Test
    public void sendGroupMessage(){
        app.toManageToolsPage();
        String title = "开心6";
        List<String> msgTitle = broadCastPage
                .messageSend(title,"新年好","祝福","向雅")
                .getListMsg().subList(0,5);
        System.out.println(msgTitle);
        assertThat(msgTitle,hasItem(title));
    }

    @AfterClass
    public static void quit(){
        app.quit();
    }

}
