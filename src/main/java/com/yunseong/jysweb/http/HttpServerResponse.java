package com.yunseong.jysweb.http;

import java.util.HashMap;
import java.util.Map;

public class HttpServerResponse {

    private Map<String, String> headers;
    private String body;

    public HttpServerResponse() {
        this.headers = new HashMap<>();
    }

    public HttpServerResponse addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpServerResponse body(String body) {
        this.body = body;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK \r\n");
        this.headers.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\r\n"));
        sb.append("\r\n");
        sb.append(body);
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
