package com.shiyou.arbitrage.common;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;


public class ObjectMapper {

    private static Gson gson = create();

    private static Gson create() {
        JsonSerializer<Date> ser = (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(src.getTime());
        JsonDeserializer<Date> deSer = (json, typeOfT, context) -> json == null ? null : new Date(json.getAsLong());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BigDecimal.class, new BigDecimalTypeAdapter());
        builder.registerTypeAdapter(Double.class, new DoubleTypeAdapter());
        builder.registerTypeAdapter(Integer.class, new IntegerTypeAdapter());
        builder.registerTypeAdapter(Long.class, new LongTypeAdapter());
        builder.registerTypeAdapter(Date.class, ser);
        builder.registerTypeAdapter(Date.class, deSer);
        return builder.create();
    }

    public static String toJson(Object instance) {
        return gson.toJson(instance);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
