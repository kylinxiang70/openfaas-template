package org.kylin.openfaas;

public class Handler implements IHandler {
    @Override
    public IResponse handle(IRequest request) {
        Header header = new Header();
        header.setHeader("content-type", "text/plain");
        return new Response(200, "hello world", header);
    }
}