import java.io.BufferedInputStream;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class JsonParser {
    public static String filePath;

    public Config parse(String filePath) throws IOException {
        Config config = new Config();
        filePath = filePath;
        Map<String, String> resultMap = (Map<String, String>) parseJson("config.json");
        for (String key : resultMap.keySet()) {
            if ("webroot".equalsIgnoreCase(key))
                config.setWebroot(resultMap.get(key));
            else if ("port".equalsIgnoreCase(key))
                config.setPort(resultMap.get(key));
        }
        return config;
    }

    public Object parseJson(String filePath) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        int ch;
        StringBuilder jsonBuilder = new StringBuilder();
        while ((ch = bufferedInputStream.read()) != -1) {
            jsonBuilder.append((char) ch);
        }
        String filteredString = jsonBuilder.toString().trim().replaceAll("[{}\"]", "");
        // System.out.println("Filtered:"+filteredString);
        String[] filteredStrings = filteredString.split(",");
        Map<String, String> jsonMapping = new HashMap<>();
        for (String string : filteredStrings) {
            string = string.trim().replaceAll("[\n]", "");
            jsonMapping.put(string.split(":")[0], string.split(":")[1]);
        }
        return jsonMapping;
    }
    public String writeJson(Map<String,Object> resultMap){
        int count=0;
        StringBuilder jsonResult=new StringBuilder();
        jsonResult.append("{\n");
        for(String key:resultMap.keySet()){
            count++;
            jsonResult.append('"').append(key).append('"').append(":").
                    append('"').append(resultMap.get(key)).append('"');
            if(count!= resultMap.size())
                jsonResult.append(",");
            jsonResult.append("\n");
        }
        count=0;
        jsonResult.append("}");
        return jsonResult.toString();
    }
}
