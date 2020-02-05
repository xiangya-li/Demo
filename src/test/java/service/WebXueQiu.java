package service;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/29 19:34
 * @Version :1.0
 */
public class WebXueQiu {
    @BeforeClass
    public static void setup(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);
        RestAssured.filters((req,res,ctx)->{
            if(req.getURI().contains("xueqiu.com")){
                req.cookie("device_id=24700f9f1986800ab4fcc880530dd0ed; cookiesu=481580273518153; Hm_lvt_1db88642e346389874251b5a1eded6e3=1580092012,1580273518,1580346292; s=dw12ac61by; xq_a_token=f67469e1bd68b4c9055bd33e2734c3334984e072; xqat=f67469e1bd68b4c9055bd33e2734c3334984e072; xq_r_token=a79e00ed7dea76139068733bededb2e6550bf553; xq_is_login=1; u=9107765083; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1580346338");
            }
            Response resOrigin = ctx.next(req,res);
            return resOrigin;
        });
    }
    @Test
    public void testSearchXueQiu(){
        given().log().all()
                .queryParam("code","sogo")
                .when()
                .get("https://xueqiu.com/stock/search.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("stocks.name",hasItems("搜狗"))
                .body("stocks.code",hasItems("SOGO"));

    }

    @Test
    public void testHotStockList(){
        given().log().all()
                .queryParam("size","8")
                .queryParam("type","10")
                .when()
                .get("https://stock.xueqiu.com/v5/stock/hot_stock/list.json")
                .then()
                .log().all()
                .statusCode(200)
                /*.body("data.items[0].type",equalTo(10))
                .body("data.items.size()",equalTo(8))
                .body("data.items.find{ items -> items.name == '腾讯控股' }.code",equalTo("00700"))
                .body("data.items.name",hasItems("融创中国","融创中国","中国医疗集团","中国平安","海底捞","小米集团-W","万科企业"))*/;
    }

    @Test
    public void testInJsonBase64(){
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
                .queryParam("size","8")
                .queryParam("type","10")
                .when()
                .get("https://stock.xueqiu.com/v5/stock/hot_stock/list.json").prettyPeek()
                .then()
                .statusCode(200);
    }
}
