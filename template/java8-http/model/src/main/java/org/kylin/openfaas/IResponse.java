package org.kylin.openfaas;

public interface IResponse {
    String getBody();

    void setBody(String body);

    Header getHeaders();

    void setHeaders(Header header);

    int getStatusCode();

    void setStatusCode(int code);
}
