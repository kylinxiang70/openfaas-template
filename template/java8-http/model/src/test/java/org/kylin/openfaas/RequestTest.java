package org.kylin.openfaas;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.kylin.openfaas.TestUtil.getMethodByName;

public class RequestTest {
    @Test
    void testGetBody() {
        Request request = genReqWithBodyAndHeaderAndPathAndQuery();
        assertEquals("test body", request.getBody());
    }

    @Test
    void testGetHeaders() {
        Request request = genReqWithBodyAndHeaderAndPathAndQuery();
        assertNotNull(request.getHeaders());
        assertEquals("v1", request.getHeaders().getHeader("k1").get(0));
    }

    @Test
    void testGetQueryRow() {
        Request request = genReqWithBodyAndHeaderAndPathAndQuery();
        assertEquals("a=1&b=2", request.getQueryRaw());
    }

    @Test
    void testGetQueryRowWithoutQuery() {
        Request request = genReqWithoutQuery();
        assertNull(request.getQueryRaw());
    }

    @Test
    void testGetQuery() {
        Request request = genReqWithBodyAndHeaderAndPathAndQuery();
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("a", Arrays.asList("1"));
        expected.put("b", Arrays.asList("2"));
        assertEquals(expected, request.getQuery());
    }

    @Test
    void testGetPathRow() {
        Request request = genReqWithBodyAndHeaderAndPathAndQuery();
        assertEquals("/aaa/bbb/ccc", request.getPathRaw());
    }

    @Test
    void testGetPathRowWithoutPath() {
        Request request = genReqWithoutPath();
        assertEquals("", request.getPathRaw());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testGetQueryParameters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Request request = genReqWithFullQuery();
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("a", Arrays.asList(""));
        expected.put("b", Arrays.asList("2"));
        expected.put("d", Arrays.asList("4", "5"));

        Method method = getMethodByName(request, "parseQueryParameters", null);

        Map<String, List<String>> actual = (Map<String, List<String>>) method.invoke(request);

        assertEquals(expected, actual);
    }

    private Request genReqWithBodyAndHeaderAndPathAndQuery() {
        String body = "test body";
        Header header = new Header();
        header.setHeader("k1", "v1");
        String uri = "/aaa/bbb/ccc?a=1&b=2";
        return new Request(body, header, uri);
    }

    private Request genReqWithoutQuery() {
        String body = "test body";
        Header header = new Header();
        header.setHeader("k1", "v1");
        String uri = "/aaa/bbb/ccc";
        return new Request(body, header, uri);
    }

    private Request genReqWithoutPath() {
        String body = "test body";
        Header header = new Header();
        header.setHeader("k1", "v1");
        String uri = "";
        return new Request(body, header, uri);
    }

    private Request genReqWithFullQuery() {
        String body = "test body";
        Header header = new Header();
        header.setHeader("k1", "v1");
        String uri = "/aaa/bbb/ccc?a=&b=2&=3&d=4&d=5";
        return new Request(body, header, uri);
    }
}

