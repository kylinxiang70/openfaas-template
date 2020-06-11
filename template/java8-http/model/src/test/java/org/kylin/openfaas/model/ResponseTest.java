package org.kylin.openfaas.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kylin.openfaas.model.TestUtil.getRtField;

public class ResponseTest {
    @Test
    void testGetBody() {
        Response res = genSuccessResponse();
        assertEquals("test body", res.getBody());
    }

    @Test
    void testSetBody() throws NoSuchFieldException, IllegalAccessException {
        Response res = new Response();
        res.setBody("test body");

        String actual = (String) getRtField("body", res);
        assertEquals("test body", actual);
    }

    @Test
    void testGetStatusCodeDefaultValue() {
        Response response = new Response();
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void testGetStatusCode() {
        Response response = new Response();
        response.setStatusCode(404);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    void testSetStatusCode() throws NoSuchFieldException, IllegalAccessException {
        Response response = new Response();
        response.setStatusCode(500);
        int actual = (int) getRtField("statusCode", response);
        assertEquals(500, actual);
    }

    @Test
    void testGetHeaders() {
        Response res = genSuccessResponse();
        Header header = res.getHeaders();
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("content-type", Arrays.asList("application/json"));
        assertEquals(expected, header.entries());
    }

    @Test
    void testSetHeaders() throws NoSuchFieldException, IllegalAccessException {
        Response res = genSuccessResponse();
        Header header = new Header();
        header.setHeader("content-type", "application/json");
        res.setHeaders(header);

        Header actual = (Header) getRtField("headers", res);
        assertEquals(header.entries(), actual.entries());
    }


    private Response genSuccessResponse() {
        String body = "test body";
        Header header = new Header();
        header.setHeader("content-type", "application/json");
        return new Response(200, body, header);
    }
}
