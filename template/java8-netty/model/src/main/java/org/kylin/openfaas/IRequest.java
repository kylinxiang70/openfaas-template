package org.kylin.openfaas;

import java.util.List;
import java.util.Map;

public interface IRequest {
    String getBody();

    IHeader getHeaders();

    String getQueryRaw();

    Map<String, List<String>> getQuery();

    String getPathRaw();
}
