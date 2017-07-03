package com.ericsson.csp.tsc.admin.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JacksonUtil {

    public final static ObjectMapper MAPPER = new ObjectMapper();

    private JacksonUtil() {
    }

    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MAPPER.setDateFormat(df);
    }

    public static List getListByTargetClass(String json, Class clss) throws IOException {
        JavaType javaType = getCollectionType(ArrayList.class, clss);
        return (List) MAPPER.readValue(json, javaType);
    }

    /**
     * 获取泛型的Collection Type
     * 
     * @param collectionClass
     *            泛型的Collection
     * @param elementClasses
     *            元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T parseJsonTOObject(String json, Class<T> clss) throws IOException {
        return MAPPER.readValue(json, clss);
    }

    public static String parseObjectToJson(Object o) throws IOException {
        return MAPPER.writeValueAsString(o);
    }

}
