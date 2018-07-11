package cn.droidlover.xdroid.kit;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import cn.droidlover.xdroidbase.kit.DateUtil;
import cn.droidlover.xdroidbase.kit.LogUtil;

/**
 * Gson的工具类
 * @author yuanlu
 * @version 1.0
 * @since 2015-4-29
 */
final public class GsonUtil {
    private static final boolean DEBUG = true;
    private static Gson gson = null;

    /**
     * 创建Gson对象
     */
    static {
        getInstance();
    }

    /**
     * 不需要外部通过构造函数使用GsonUtils
     */
    private GsonUtil() {

    }

    public static Gson getInstance() {
        if (gson == null) {
            /* 创建Gson对象 */
//            gson = new Gson();
            gson = new GsonBuilder()
//                    .setVersion(1.1)    // 设置版本
//                    .excludeFieldsWithoutExposeAnnotation()    // 不解析@expose标识的字段
//                    .setDateFormat("yyyy-MM-dd")    // 在序列化和反序化时均生效
//                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PRIVATE) // 不序列反序列化的修饰符
                    .addSerializationExclusionStrategy(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                            final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                            return expose != null && !expose.serialize();
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> aClass) {
                            return false;
                        }
                    })
//                    .disableInnerClassSerialization() // 禁此序列化内部类
//                    .enableComplexMapKeySerialization()        // 使能复杂的Map<key, value>解析方式
//                    .excludeFieldsWithModifiers(Modifier.PROTECTED)
//                    .serializeNulls()        // 输出空引用
//                    .setPrettyPrinting()    // 控制缩进，在转换成json字符串时添加缩进功能，一般只有打印时需要
//                    .registerTypeAdapter(Date.class, new DateSerializerAdapter())    // 自定义序列化反序列化Date
//                    .generateNonExecutableJson()  // 生成不可执行的Json（多了 )]}' 这4个字符）
//                    .disableHtmlEscaping()    // 禁止转义html标签
                    .setDateFormat(DateUtil.FORMAT_SHORT)
                    .create();
        }
        return gson;
    }

    /**
     * 将Java对象转换为json字符串
     * @param ts
     * @return JsonString
     */
    public static String toJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * 将json字符串转换为list
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {
        if (DEBUG) LogUtil.d("jsonToList: " + jsonStr);
        List<?> objList = null;
        if (gson != null) {
            Type type = new TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json字符串解析为指定类型的list
     * @param jsonStr
     * @param type
     * @return
     */
    public static List<?> jsonToList(String jsonStr, Type type) {
        List<?> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json字符串转换为Map
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        if (DEBUG) LogUtil.d("jsonToMap: " + jsonStr);
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 将json字符串转换为指定类型的map
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr, Type type) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 将json字符串转换为指定类型的Java对象
     * @param jsonStr
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> cl) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonStr, cl);
        }
        return t;
    }

    /**
     * 获取指定key的value对象
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getValueFromMap(String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    /**
     * 获取指定key的value对象
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getValueFromList(String jsonStr, String key) {
        Object rulsObj = null;
        List<?> rulsList = jsonToList(jsonStr);
        if (rulsList != null && rulsList.size() == 1) {
            rulsObj = getValueFromMap(toJson(rulsList.get(0)), key);
        }
        return rulsObj;
    }

//    /**
//     * 序列化含有Data对象的对象
//     *
//     * @param ts
//     * @return
//     */
//    @SuppressLint("SimpleDateFormat")
//    public static String objectToJsonDateSerializer(Object ts,
//                                                    final String dateformat) {
//        String jsonStr = null;
//        // 创建一个自定义序列化的Gson对象，指定Data类型的序列化方式
//        Gson gson = new GsonBuilder()
//                .registerTypeHierarchyAdapter(Date.class,
//                        new JsonSerializer<Date>() {
//                            @Override
//                            public JsonElement serialize(Date src, Type arg1,
//                                                         JsonSerializationContext arg2) {
//                                SimpleDateFormat format = new SimpleDateFormat(
//                                        dateformat);
//                                return new JsonPrimitive(format.format(src));
//                            }
//                        }).setDateFormat(dateformat).create();
//        if (gson != null) {
//            jsonStr = gson.toJson(ts);
//        }
//
//        return jsonStr;
//    }
//
//    /**
//     * 反序列化含有Data的json字符串
//     *
//     * @param jsonStr
//     * @param cl
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @SuppressLint("SimpleDateFormat")
//    public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl,
//                                                 final String pattern) {
//        Object obj = null;
//        // 创建一个自定义序列化的Gson对象，指定Data对象的反序列化方式
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//                    @Override
//                    public Date deserialize(JsonElement json, Type arg1,
//                                            JsonDeserializationContext arg2)
//                            throws JsonParseException {
//                        SimpleDateFormat format = new SimpleDateFormat(pattern);
//                        String dateStr = json.getAsString();
//                        try {
//                            return format.parse(dateStr);
//                        } catch (java.text.ParseException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                }).setDateFormat(pattern).create();
//        if (gson != null) {
//            obj = gson.fromJson(jsonStr, cl);
//        }
//
//        return (T) obj;
//    }
//
//    /**
//     * Enum的序列化方法
//     *
//     * @param jsonStr, cl
//     * @return Object
//     */
//    public static Object jsonToObjectSerializer(String jsonStr, Class<?> cl) {
//        Object obj = null;
//        Gson gson = new GsonBuilder()
//                .registerTypeHierarchyAdapter(PackageState.class,
//                        new EnumSerializer()).create();
//        if (gson != null) {
//            obj = gson.fromJson(jsonStr, cl);
//        }
//
//        return obj;
//    }
}
