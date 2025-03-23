package com.dct.nextgen.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Utils {

    public static <T> Map<String, Class<?>> getObjectFields(Class<T> mappingClass) {
        Map<String, Class<?>> fieldMap = new HashMap<>();

        for (Field field : mappingClass.getDeclaredFields()) {
            fieldMap.put(field.getName(), field.getType());
        }

        return fieldMap;
    }
}
