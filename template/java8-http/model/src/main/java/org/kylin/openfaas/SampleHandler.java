package org.kylin.openfaas;

public class SampleHandler implements IHandler {

    @Override
    public IResponse handle(IRequest request) {
        return new Response();
    }
}
