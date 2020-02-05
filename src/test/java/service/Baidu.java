package service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/29 11:58
 * @Version :1.0
 */
public class Baidu {
    public static String code;
    @Test
    public void testGetHtml(){
        given()
                .log().all().get("http://www.baidu.com")
                .then().log().all().statusCode(200);
    }


    @Test
    public void testPostJson(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("a","1");
        map.put("b","testerHome");
        map.put("c",new String[]{"111","211"});
        given().log().all()
                .contentType(ContentType.JSON)
                .body(map)
                .when().post("http://www.baidu.com")
                .then().log().all().statusCode(200);
    }
}
