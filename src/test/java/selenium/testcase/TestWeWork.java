package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import selenium.page.App;
import selenium.page.ContactPage;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/3 17:16
 * @Version :1.0
 */
public class TestWeWork {
    public static App app;
    public static ContactPage contactPage;
    String phone = "13300110002";
    @BeforeClass
    public static void beforeAll(){
        app = new App();
        contactPage = new ContactPage();
        app.LoginWithCookies();
    }

    @Test
    public void add(){
        app.toMember();
        contactPage.add(phone,phone,phone);
    }

    @Test
    public void delete() throws InterruptedException {
        app.toContact();
        contactPage.delete(phone);
    }

    @Test
    public void deleteContactPage(){
        app.toContact();
        contactPage.deleteContactPage();
    }

    @Test
    public void importFromFile(){
        app.toContact();
        contactPage.importFromFile("C:\\Users\\Administrator\\Desktop\\通讯录批量导入模板.xlsx");
    }


    @AfterClass
    public static void quit(){
        app.quit();
    }




}
