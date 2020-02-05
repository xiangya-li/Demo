package template;

import com.github.mustachejava.DefaultMustacheFactory;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 23:09
 * @Version :1.0
 */
public class Template {
    public String template(HashMap<String,Object> data, String path)  {
        Writer writer = new StringWriter();
        new DefaultMustacheFactory().compile(path).execute(writer,data);
        return writer.toString();
    }
}
