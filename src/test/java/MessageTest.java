import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.Matchers.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/30 21:06
 * @Version :1.0
 */
public class MessageTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "测试中文",
            "ありがとう",
            "你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。",
            "<script>alert(00)</script>"})

    void testSendMessage(String con){

        Message msg = new Message();
        msg.send("@all",con,Config.getInstance().agentId, "data/json/messageSend.json")
                .then().body("errmsg",equalTo("ok"));
    }
}
