import io.restassured.response.Response;
import template.Template;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 22:57
 * @Version :1.0
 */
public class Department {
    public static Integer updateDepart = 6;

    public Response createDepartment(String data){
        return
                given().log().all()
                        .queryParam("access_token",Config.getInstance().txlToken)
                        .contentType("application/json")
                        .body(data)
                .when()
                        .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                        .statusCode(200)
                .extract().response();

    }

    public Response createDepartment(String name,Integer parentid,String path){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("parentid",parentid);
        map.put("id",Config.getInstance().parentDepartmentId);
        Template template = new Template();
        return createDepartment(template.template(map,path));
    }

    public Response listDepartment(int id){
        return
                given()
                        .queryParam("access_token",Config.getInstance().txlToken)
                        .queryParam("id",id)
                        .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().statusCode(200)
                        .extract().response();

    }
     public Response deleteDepartment(int id){
        return
                given().log().all()
                        .queryParam("access_token",Config.getInstance().txlToken)
                        .queryParam("id",id)
                        .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().statusCode(200)
                        .extract().response();

     }

     public Response updateDepartment(String data){
        return
         given().log().all()
                 .queryParam("access_token",Config.getInstance().txlToken)
                 .contentType("application/json")
                 .body(data)
                 .when()
                 .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                 .then()
                 .statusCode(200)
                 .extract().response();
    }

     public Response updateDepartment(String name,String path){
         HashMap<String,Object> map = new HashMap<>();
         map.put("name",name);
         map.put("id",updateDepart);
         Template template = new Template();
         return updateDepartment(template.template(map,path));

     }
}
