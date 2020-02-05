import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 22:53
 * @Version :1.0
 */
public class DepartmentTest {
    static Department department = new Department();
    @BeforeAll
    public static void beforeAll(){
        ArrayList<Integer> ids = department.listDepartment(Config.getInstance().parentDepartmentId)
                .then()
                .extract()
                .body()
                .path("department.findAll {d->d.parentid == "+Config.getInstance().parentDepartmentId+"}.id");
        ids.forEach(id->department.deleteDepartment(id));
        department.updateDepartment("重置为修改前", "data/json/departmentUpdate.json");


    }


    @Test
    public void listDepartment(){
        department.listDepartment(Config.getInstance().parentDepartmentId)
                .then().log().all();
    }

    @Test
    public void deleteDepartment(){
        department.deleteDepartment(Config.getInstance().parentDepartmentId)
                .then().log().all()
                .body("errmsg",equalTo("deleted"));
    }

    @Test
    public void createDepartment(){
        department.createDepartment("李向雅",1, "data/json/departmentCreate.json")
                .then().log().all()
                .body("errmsg",equalTo("created"));
    }

    @Test
    public void updateDepartment(){
        department.updateDepartment("修改后", "data/json/departmentUpdate.json")
                .then().log().all()
                .body("errmsg",equalTo("updated"));
    }

}
