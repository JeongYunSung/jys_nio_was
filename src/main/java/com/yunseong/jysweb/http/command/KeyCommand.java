package com.yunseong.jysweb.http.command;

import com.yunseong.jysweb.http.HttpServer;

import java.io.IOException;

public interface KeyCommand {

    void execute(HttpServer httpServer) throws IOException;
}
