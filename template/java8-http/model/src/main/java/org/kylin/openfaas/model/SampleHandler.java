package org.kylin.openfaas.model;

public class SampleHandler implements IHandler {

    @Override
    public IResponse handle(IRequest request) {
        return new Response();
    }
}
