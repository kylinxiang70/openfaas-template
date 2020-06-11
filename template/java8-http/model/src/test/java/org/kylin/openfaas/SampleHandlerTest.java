package org.kylin.openfaas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleHandlerTest {
    @Test
    void testHandle() {
        SampleHandler handler = new SampleHandler();
        Request request = new Request("body", new Header(), "/aaa/bbb");
        IResponse response = handler.handle(request);
        assertEquals(200, response.getStatusCode());
    }
}
