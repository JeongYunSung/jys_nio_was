package com.yunseong.jysweb.http.command;

import com.yunseong.jysweb.http.HttpServer;

public class EmptyCommand implements KeyCommand {

    @Override
    public void execute(HttpServer httpServer) {
    }
}
