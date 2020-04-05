package web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Properties;

/**
*
* @author qushiwen
* @create 2018-01-02
* @version 1.0
**/
public class JsonUtil {

    private static Gson gson = null;
    static{
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();
        gson = gsonBuilder.create();
    }


    public static Gson getGson() {
        return  gson;
    }

    public static String toJson(Object obj) {
        return gson.toJson( obj );
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return  gson.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, TypeToken<T> ref)  {
        return gson.fromJson(json, ref.getType() );
    }

    public static Properties fromJson2Props(String json) {
        Map<String, Object> mapVal = fromJson(json, new TypeToken<Map<String, Object>>() {} );
        Properties connProps = new Properties();
        for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
            connProps.put(entry.getKey(), entry.getValue());
        }
        return connProps;
    }

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    public static boolean isGoodJson(String json) {
        if ( StringUtils.isEmpty( json ) ) {
            return false;
        }
        try {
            if(new JsonParser().parse(json).isJsonObject() )
                return true;
            else
                return false;
        } catch (JsonParseException e) {
            return false;
        }
    }


}
