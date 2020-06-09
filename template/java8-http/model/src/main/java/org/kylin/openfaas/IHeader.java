package org.kylin.openfaas;

import java.util.List;
import java.util.Map;

public interface IHeader {
    void setHeader(String key, String value);

    List<String> getHeader(String key);

    Map<String, List<String>> entries();
}
