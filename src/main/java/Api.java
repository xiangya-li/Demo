import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarRequest;

import java.io.File;
import java.net.URLDecoder;

/**
 * @Author : xiangya
 * @Description :
 * @Date : 2020/1/31 17:40
 * @Version :1.0
 */
/*public class Api {
    public Restful getApiFromHar(String path,String pattern){

        HarReader harReader = new HarReader();
        try{
        Har har = harReader.readFromFile(new File(
                URLDecoder.decode(
                        getClass().getResource(path).getPath(),"utf-8"
                )));
        HarRequest request = new HarRequest();
        Boolean match = false;
        for (HarEntry entry : har.getLog().getEntries()){
            request= entry.getRequest();
            if (request.getUrl().matches(pattern)){
                match = true;
                break;
            }
        }
        if (match == true){
            request = null;
            throw new Exception("ddd");
        }
        Restful restful = new Restful();
        restful.method = request.getMethod().name().toLowerCase();
        restful.url = request.getUrl();
        request.getQueryString().forEach(q -> {
        restful.query.put(q.getName(),q.getValue());
        });
        restful.body = request.getPostData().getText();

    }catch (Exception e){
            System.out.println("xxx");
        } return restful;
        }
}*/
