import com.github.mustachejava.DefaultMustacheFactory;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 10:22
 * @Version :1.0
 */
public class Message {
    public Response send(String data){
        return
                given().log().all()
                        .queryParam("access_token",Config.getInstance().msgToken)
                        .contentType("application/json")
                        .body(data)
                        .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then().log().all()
                        .extract().response();
}

    public Response send(String touser,String content,Integer agentid,String path) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("touser",touser);
        map.put("content",content);
        map.put("agentid",agentid);
        return send(template(map,path));
    }

    public String template(HashMap<String,Object> data,String path)  {
        Writer writer = new StringWriter();
        new DefaultMustacheFactory().compile(path).execute(writer,data);
        return writer.toString();
    }
}
