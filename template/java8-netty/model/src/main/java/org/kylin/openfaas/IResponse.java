package org.kylin.openfaas;

public interface IResponse {
    String getBody();

    void setBody(String body);

    IHeader getHeaders();

    void setHeaders(IHeader header);

    int getStatusCode();

    void setStatusCode(int code);
}
