package com.yunseong.jysweb.http;

public interface HttpServerProcessor {

    void process(HttpServerRequest request, HttpServerResponse response);
}
