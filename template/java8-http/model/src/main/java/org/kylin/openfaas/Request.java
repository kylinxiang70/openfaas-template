package org.kylin.openfaas;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Request implements IRequest {
    private Header headers;
    private String body;
    private Map<String, List<String>> queryParameters;
    private URI uri;
    private String queryRaw;
    private String pathRaw;

    public Request(String body, Header headers, String uri) {
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
    public Header getHeaders() {
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
            String[] pairs = queryRaw.split("[&]");

            for (String pair : pairs) {
                if (pair.charAt(0) == '=') continue; // key is empty
                String[] param = pair.split("[=]");

                String key = param[0];
                String value = param.length == 2 ? param[1] : ""; // allow value is empty

                if (queryParamsMap.containsKey(key)) {
                    queryParamsMap.get(key).add(value);
                } else {
                    List<String> values = new ArrayList<>();
                    values.add(value);
                    queryParamsMap.put(key, values);
                }
            }
        }
        return queryParamsMap;
    }
}
