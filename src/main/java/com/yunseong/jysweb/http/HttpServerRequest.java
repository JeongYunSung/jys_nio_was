package com.yunseong.jysweb.http;

import java.util.Map;

public class HttpServerRequest {

    private String url;
    private HttpMethod method;
    private Map<String, String> headers;
    private Map<String, String> params;

    public HttpServerRequest(String url, HttpMethod method, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
    }

    public String getUrl() {
        return this.url;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}
