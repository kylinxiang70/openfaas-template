package org.kylin.openfaas.model;

import org.junit.jupiter.api.Test;
import org.kylin.openfaas.model.Header;
import org.kylin.openfaas.model.IResponse;
import org.kylin.openfaas.model.Request;
import org.kylin.openfaas.model.SampleHandler;

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
