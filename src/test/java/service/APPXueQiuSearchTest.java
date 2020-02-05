package service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/29 17:24
 * @Version :1.0
 */
public class APPXueQiuSearchTest {
    public static Long uid;


    @Test
    public void testXueQiuLogin(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);
        uid = given()
                .header("User-Agent","Xueqiu iPhone 12.3")
                .queryParam("_s","32a91a")
                .queryParam("_t","1982CC9C-D65D-43AE-BB5A-2E8F7BD23B9E.1169579874.1580286272751.1580287444772")
                .queryParam("x","0.97")
                .cookie("xq_a_token","0833a5c2d5f1919724e57f0ab2d85fd47a30e21c")
                .cookie("xq_id_token","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjExNjk1Nzk4NzQsImlzcyI6InVjIiwiZXhwIjoxNTgyODc1MjgyLCJjdG0iOjE1ODAyODU1NDY3NTEsImNpZCI6IldpQ2lteHBqNUgifQ.TEA1EmQBo21xvaOypQ8NL19cF9pip9bQ9DVk9ZoQJeuRAIbBztbMIgb20DeHGEYLJGO6rUJeluA-MTp66401cAYgMysUORVcBZWN3dRys-r-SWXd4jKnqIehp5xHYkWQSN4lPoow5SyzogTMsQnSGA_W4nT_vGtuJyQjFY1s9g23eLcmfdJFrkyQGHUE_DIoqQzCOGAttpXD81msRrgUyAvQamSaCvuV7tTB1AnfUjHyqMCSoLiD6diZA8WAoHH9YBXkYJKASBRBhJRnXcH-3ZWRHCp9gLT11hpkzKXPmXHk8tkR6zQNmjoQGP8qMyg1zoACvAiJ6-6S0pP1db7L0w")
                .cookie("u","1169579874")
                .formParam("areacode","86")
                .formParam("captcha","")
                .formParam("client_id","WiCimxpj5H")
                .formParam("client_secret","TM69Da3uPkFzIdxpTEm6hp")
                .formParam("device_uuid","1982CC9C-D65D-43AE-BB5A-2E8F7BD23B9E")
                .formParam("grant_type","password")
                .formParam("password","802da7601453b0fe838e6ceb6b2607de")
                .formParam("is_register","0")
                .formParam("sid","1982CC9C-D65D-43AE-BB5A-2E8F7BD23B9E")
                .formParam("telephone","15757160491")
                .when().post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("uid");
        System.out.println(uid);
    }

    @Test
    public void testSearchAll(){
        given()
                .header("User-Agent","Xueqiu iPhone 12.3")
                .queryParam("count","10")
                .queryParam("page","1")
                .queryParam("q","sogo")
                .queryParam("sortId","1")
                .queryParam("source","all")
                .queryParam("tabId","1")
                .cookie("xq_a_token","7e646c5144e7582819f45f0095db53d79c5451ab")
                .cookie("u",uid)
        .when()
                .get("https://api.xueqiu.com/query/v1/search/all")
        .then().log().all()
                .statusCode(200)
                .body("success",equalTo("true"));
    }




}
