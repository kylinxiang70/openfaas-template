package org.kylin.openfaas;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Request implements IRequest {
    private IHeader headers;
    private String body;
    private Map<String, List<String>> queryParameters;
    private URI uri;
    private String queryRaw;
    private String pathRaw;

    public Request(String body, IHeader headers, String uri) {
        this.body = body;
        this.headers = headers;
        this.uri = URI.create(uri);
        this.queryRaw = this.uri.getRawQuery();
        this.pathRaw = this.uri.getRawPath();
        this.queryParameters = this.parseQueryParameters();
    }


    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public IHeader getHeaders() {
        return this.headers;
    }

    @Override
    public String getQueryRaw() {
        return this.queryRaw;
    }

    @Override
    public Map<String, List<String>> getQuery() {

        return this.queryParameters;
    }

    @Override
    public String getPathRaw() {
        return this.pathRaw;
    }

    private Map<String, List<String>> parseQueryParameters() {
        Map<String, List<String>> queryParamsMap = new HashMap<>();
        if (queryRaw != null) {
            String pairs[] = queryRaw.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = param[0];
                String value = param[1];
                if (null != key && key.length() > 0) {
                    if (queryParamsMap.containsKey(key)) {
                        queryParamsMap.get(key).add(value);
                    } else {
                        List<String> values = new ArrayList<>();
                        values.add(value);
                        queryParamsMap.put(key, values);
                    }
                }
            }
        }
        return queryParamsMap;
    }
}
