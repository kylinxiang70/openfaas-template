package org.kylin.openfaas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header implements IHeader {
    private final Map<String, List<String>> map;

    public Header() {
        map = new HashMap<>();
    }

    @Override
    public void setHeader(String key, String value) {
        if (!isNullOrEmptyString(key)) { // if key is null or empty, skip
            if (map.containsKey(key)) {
                map.get(key).add(value);
            } else {
                List<String> values = new ArrayList<>();
                values.add(value);
                map.put(key, values);
            }
        }
    }

    @Override
    public List<String> getHeader(String key) {
        if (isNullOrEmptyString(key)) throw new IllegalArgumentException("key can not be null or empty.");
        return map.get(key);
    }

    @Override
    public Map<String, List<String>> entries() {
        return map;
    }

    @Override
    public int size() {
        return map.size();
    }

    private boolean isNullOrEmptyString(String str) {
        return null == str || str.length() <= 0;
    }
}
