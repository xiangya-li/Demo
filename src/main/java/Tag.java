import io.restassured.response.Response;
import template.Template;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/2/1 19:33
 * @Version :1.0
 */
public class Tag {
    public Response create(String data){
        return
        given()
                .queryParam("access_token",Config.getInstance().txlToken)
                .contentType("application/json")
                .body(data)
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/create")
        .then()
                .extract().response();
    }

    public Response create(String tagname,Integer tagid ,String path){
        HashMap<String,Object> map = new HashMap<>();
        map.put("tagname",tagname);
        map.put("tagid",tagid);
        Template template = new Template();
        return create(template.template(map,path));
    }

    public void delete(){

    }

    public void list(){

    }

    public void update(){

    }
}
