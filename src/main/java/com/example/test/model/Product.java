package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Product {
    Map<String, Object> details = new LinkedHashMap<>();

    @JsonAnySetter
    public void setDetails(String key, Object value) {
        details.put(key, value);
    }

    @JsonAnyGetter
    public LinkedHashMap<String, Object> getDetails(String key) {
        return (LinkedHashMap<String, Object>) details.get(key);
    }
}