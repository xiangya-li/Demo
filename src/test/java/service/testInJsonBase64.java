package service;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/30 9:49
 * @Version :1.0
 */
public class testInJsonBase64 {
    @Test
    public void testInJsonBase64(){
        RestAssured.proxy("127.0.0.1",8888);
        given()
                .filters((req,res,ctx)->{
                    Response resOrigin = ctx.next(req,res);
                    ResponseBuilder responseBuilder = new ResponseBuilder().clone(resOrigin);
                    String decodeContent = new String(
                            Base64.getDecoder().decode(
                                    resOrigin.body().asString().trim()
                            )
                    );
                    responseBuilder.setBody(decodeContent);
                    Response resNew = responseBuilder.build();
                    return resNew;
                })
                .when()
                .get("http://127.0.0.1:9999/1.json").prettyPeek()
                .then()
                .statusCode(200);
    }
}
