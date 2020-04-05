package web.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jrqushiwen on 2017-09-22.
 */
public class GsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        Map<String, Object> mapVal = OBJECT_MAPPER.readValue("", new TypeReference<Map<String, Object>>() {
        });
        Properties connProps = new Properties();
        for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
            connProps.put(entry.getKey(), entry.getValue());
        }
    }

}
