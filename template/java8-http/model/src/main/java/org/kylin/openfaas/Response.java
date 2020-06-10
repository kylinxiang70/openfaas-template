package org.kylin.openfaas;

public class Response implements IResponse {
    private int statusCode = 200;
    private String body;
    private Header headers;

    public Response() {
    }

    public Response(int statusCode, String body, Header headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void setHeaders(Header headers) {
        this.headers = headers;
    }

    @Override
    public Header getHeaders() {
        return this.headers;
    }

    @Override
    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }
}
