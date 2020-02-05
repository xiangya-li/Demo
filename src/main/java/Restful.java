import java.util.HashMap;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 17:43
 * @Version :1.0
 */
public class Restful {
    public String url;
    public String method;
    public HashMap<String,Object> header = new HashMap<>();
    public HashMap<String,Object> query = new HashMap<>();
    public String body;
}
