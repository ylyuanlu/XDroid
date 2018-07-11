package cn.droidlover.xdroid.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by yuanlu on 2017-04-15.
 */

public class FastJson {

    private FastJson() {
        throw new AssertionError();
    }

    /**
     * 把JSON文本parse成JavaBean集合
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将JavaBean序列化为JSON文本
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return toJSONString(object, false);
    }

    /**
     * 将JavaBean序列化为带格式的JSON文本
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJSONString(Object object, boolean prettyFormat) {
        return JSON.toJSONString(object, prettyFormat);
    }

    /**
     * 将JavaBean转换为JSONObject或者JSONArray。
     * @param javaObject
     * @return
     */
    public static Object toJSON(Object javaObject) {
        return JSON.toJSON(javaObject);
    }

    /**
     * 把JSON文本parse为JSONObject或者JSONArray
     * @param text
     * @return
     */
    public static Object parse(String text) {
        return JSON.parse(text);
    }

    /**
     * 把JSON文本parse成JSONObject
     * @param text
     * @return
     */
    public static JSONObject parseObject(String text) {
        return JSON.parseObject(text);
    }

    /**
     * 把JSON文本parse成JSONArray
     * @param text
     * @return
     */
    public static JSONArray parseArray(String text) {
        return JSON.parseArray(text);
    }

    /**
     * 把JSON文本parse为JavaBean
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
