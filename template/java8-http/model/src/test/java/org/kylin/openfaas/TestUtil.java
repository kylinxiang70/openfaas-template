package org.kylin.openfaas;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

public class TestUtil {
    public static Object getRtField(String fieldName, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    public static String genRandomFixedLenStr(int len) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Method getMethodByName(Object obj, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> clazz = obj.getClass();
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }
}
