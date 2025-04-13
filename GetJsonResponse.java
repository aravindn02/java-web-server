import java.util.HashMap;
import java.util.Map;

public class GetJsonResponse {
    public static final Map<String ,Object> testMap=new HashMap<>();
    public static Map<String, Object> getMap(){
        testMap.put("name","Aravindalochanan.N");
        testMap.put("age","22");
        testMap.put("role","SDE");
        return testMap;
    }
}
