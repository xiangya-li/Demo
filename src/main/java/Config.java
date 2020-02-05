import static io.restassured.RestAssured.given;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/30 22:03
 * @Version :1.0
 */
public class Config {
    public String corpId = "wwd8c160db65ad5567";
    public String contactSecret = "QCUV6sA82y2VMfqmbV9T-OiLL80qIjI9-12Z1j6hE5U";
    public String txlSecret = "bTzZTrIppSrltezCKMBfwbTBuR6qy5-D2D6Y3jqIyYw";
    public Integer agentId = 1000002;
    public String msgToken;
    public String txlToken;
    public int parentDepartmentId = 7;

    static Config config;
    public static Config getInstance(){
        if(config == null){
            config = new Config();
            config.msgToken = config.getMsgToken();
            config.txlToken = config.getTxlToken();

        }
        return config;
    }

    public String getMsgToken(){
        msgToken = given()
                .queryParam("corpid",Config.getInstance().corpId)
                .queryParam("corpsecret",Config.getInstance().contactSecret)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .statusCode(200)
                .extract().path("access_token");
        return msgToken;
    }

    public String getTxlToken(){
        txlSecret = given()
                .queryParam("corpid",Config.getInstance().corpId)
                .queryParam("corpsecret",Config.getInstance().txlSecret)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .statusCode(200)
                .extract().path("access_token");
        return txlSecret;
    }
}
