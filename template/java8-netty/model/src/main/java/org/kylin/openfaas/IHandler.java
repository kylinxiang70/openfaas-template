package org.kylin.openfaas;

public interface IHandler {
    IResponse handle(IRequest request);
}
