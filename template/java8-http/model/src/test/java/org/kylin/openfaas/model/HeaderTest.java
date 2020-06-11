package org.kylin.openfaas.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.kylin.openfaas.model.TestUtil.*;

public class HeaderTest {

    @Test
    void testSize() throws NoSuchFieldException, IllegalAccessException {
        Header header = new Header();

        header.setHeader("k1", "v1");
        assertEquals(header.size(), 1);
        header.setHeader("k1", "v1");
        assertEquals(header.size(), 1);
        header.setHeader("k2", "v2");
        assertEquals(header.size(), 2);
    }


    @Test
    @SuppressWarnings("unchecked")
    void testSetHeaderInvalidKey() throws NoSuchFieldException, IllegalAccessException {
        Header header = new Header();
        Map<String, List<String>> map = (Map<String, List<String>>) TestUtil.getRtField("map", header);

        // null key
        header.setHeader(null, TestUtil.genRandomFixedLenStr(5));
        assertEquals(0, map.size());

        // empty key
        header.setHeader("", TestUtil.genRandomFixedLenStr(5));
        assertEquals(0, map.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSetHeaderSingleValue() throws NoSuchFieldException, IllegalAccessException {
        Header header = new Header();
        Map<String, List<String>> map = (Map<String, List<String>>) TestUtil.getRtField("map", header);

        String key = TestUtil.genRandomFixedLenStr(5);
        String value = TestUtil.genRandomFixedLenStr(5);
        assertNotNull(key);
        header.setHeader(key, value);
        assertEquals(value, map.get(key).get(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSetHeaderMultipleValue() throws NoSuchFieldException, IllegalAccessException {
        Header header = new Header();
        Map<String, List<String>> map = (Map<String, List<String>>) TestUtil.getRtField("map", header);

        String key = TestUtil.genRandomFixedLenStr(5);
        String value = TestUtil.genRandomFixedLenStr(5);
        assertNotNull(key);
        header.setHeader(key, value);
        String value2 = TestUtil.genRandomFixedLenStr(5);
        assertNotNull(key);
        header.setHeader(key, value2);

        assertEquals(Arrays.asList(value, value2), map.get(key));
    }


    @Test()
    void testGetHeaderNullKey() {
        Header header = new Header();
        // set some init key/value pairs
        header.setHeader(TestUtil.genRandomFixedLenStr(5), TestUtil.genRandomFixedLenStr(5));

        Exception e = assertThrows(IllegalArgumentException.class, () -> header.getHeader(null));
        assertEquals(e.getMessage(), "key can not be null or empty.");
    }

    @Test()
    void testGetHeaderEmptyKey() {
        Header header = new Header();
        // set some init key/value pairs
        header.setHeader(TestUtil.genRandomFixedLenStr(5), TestUtil.genRandomFixedLenStr(5));

        Exception e = assertThrows(IllegalArgumentException.class, () -> header.getHeader(""));
        assertEquals(e.getMessage(), "key can not be null or empty.");
    }

    @Test
    void testGetHeaderSingleValue() {
        Header header = new Header();
        header.setHeader("k1", "v1");
        assertEquals("v1", header.getHeader("k1").get(0));
    }

    @Test
    void testGetHeaderMultipleValue() {
        Header header = new Header();
        header.setHeader("k1", "v1");
        header.setHeader("k1", "v2");
        assertEquals(Arrays.asList("v1", "v2"), header.getHeader("k1"));
    }

    @Test
    @SuppressWarnings("all")
    void testIsNullOrEmptyString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Header header = new Header();
        Method isNullOrEmptyString = getMethodByName(header, "isNullOrEmptyString", String.class);

        boolean result = (boolean) isNullOrEmptyString.invoke(header, TestUtil.genRandomFixedLenStr(5));
        assertFalse(result);

        result = (boolean) isNullOrEmptyString.invoke(header, "");
        assertTrue(result);

        String str = null;
        result = (boolean) isNullOrEmptyString.invoke(header, str);
        assertTrue(result);
    }

    @Test
    @SuppressWarnings("unchecked")
    void TestEntries() throws NoSuchFieldException, IllegalAccessException {
        Header header = new Header();
        Map<String, List<String>> map = (Map<String, List<String>>) TestUtil.getRtField("map", header);
        Map<String, List<String>> entries = header.entries();

        assertEquals(map, entries);
    }
}