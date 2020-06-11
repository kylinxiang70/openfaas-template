package org.kylin.openfaas.model;

public interface IHandler {
    IResponse handle(IRequest request);
}
