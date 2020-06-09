package org.kylin.openfaas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header implements IHeader {
    private Map<String, List<String>> map;

    public Header() {
        map = new HashMap<>();
    }

    @Override
    public void setHeader(String key, String value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<String> values = new ArrayList<>();
            values.add(value);
            map.put(key, values);
        }
    }

    @Override
    public List<String> getHeader(String key) {
        return map.get(key);
    }

    @Override
    public Map<String, List<String>> entries() {
        return map;
    }
}
