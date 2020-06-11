package org.kylin.openfaas.model;

import java.util.List;
import java.util.Map;

public interface IRequest {
    String getBody();

    Header getHeaders();

    String getQueryRaw();

    Map<String, List<String>> getQuery();

    String getPathRaw();
}
