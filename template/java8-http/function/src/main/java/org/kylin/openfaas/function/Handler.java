package org.kylin.openfaas.function;

import org.kylin.openfaas.model.*;

public class Handler implements IHandler {
    @Override
    public IResponse handle(IRequest request) {
        Header header = new Header();
        header.setHeader("content-type", "text/plain");
        return new Response(200, "hello world", header);
    }
}