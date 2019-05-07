package jfun.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @Auther: miv
 * @Date: 2019-05-06 15:42
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 格式化json的工具
 */
public class GsonUtil {
    /**
     * 格式化json字符串
     * @param json
     * @return
     */
    public static String prettyJSON(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    /**
     * 格式化json
     * @param obj
     * @return
     */
    public static String prettyJSON(Object obj){
        Gson g = new Gson();
        String json = g.toJson(obj);
        return prettyJSON(json);
    }
}
