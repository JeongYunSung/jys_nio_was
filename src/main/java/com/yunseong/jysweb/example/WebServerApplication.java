package com.yunseong.jysweb.example;

import com.yunseong.jysweb.http.HttpMethod;
import com.yunseong.jysweb.http.HttpServer;

import java.net.InetSocketAddress;

public class WebServerApplication {

    public static void main(String args[]) {
        HttpServer httpServer =
                new HttpServer(
                        new InetSocketAddress(8080)
                        , ((request, response) -> {
                            if(request.getMethod() == HttpMethod.GET) {
                                if(request.getUrl().equals("/")) {
                                    response.addHeader("Content-Type", "text/html;charset=utf-8")
                                            .body("Hello World!");
                                }else if(request.getUrl().equals("/test")) {
                                    response.addHeader("Content-Type", "text/html;charset=utf-8")
                                            .body("Welcome to Test World!");
                                }else if(request.getUrl().equals("/json")) {
                                    response.addHeader("Content-Type", "application/json;charset=utf-8")
                                            .body("json test");
                                }
                            }
                        }));
        httpServer.start();
    }
}
