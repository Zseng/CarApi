package com.zseng.car.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class DataResponse {
    private int code;
    private Map<String, Object> data;

    public DataResponse() {
        this(0);
    }

    public DataResponse(int code) {
        this.code = code;
        this.data = new HashMap<String, Object>();
    }

    public static DataResponse create() {
        return new DataResponse();
    }

    public DataResponse putAll(Map<String, Object> data) {
        this.data.putAll(data);
        return this;
    }

    public DataResponse put(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    public DataResponse putPage(String key, Page page) {
        this.data.put(key, page.getContent());
        this.data.put("totalPages", page.getTotalPages());
        this.data.put("number", page.getNumber());
        return this;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, Object> getData() {
        return data;
    }
}
