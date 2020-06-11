package org.kylin.openfaas.function;

import org.junit.jupiter.api.Test;
import org.kylin.openfaas.function.Handler;
import org.kylin.openfaas.model.Header;
import org.kylin.openfaas.model.IResponse;
import org.kylin.openfaas.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlerTest {
    @Test
    void testHandle() {
        Request req = new Request("body", new Header(), "/a/b/c?a=1&b=2");
        IResponse res = new Handler().handle(req);
        assertEquals(200, res.getStatusCode());
        assertEquals("hello world", res.getBody());
    }
}
