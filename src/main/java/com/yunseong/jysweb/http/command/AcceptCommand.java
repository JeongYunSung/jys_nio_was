package com.yunseong.jysweb.http.command;

import com.yunseong.jysweb.http.HttpServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class AcceptCommand implements KeyCommand {

    @Override
    public void execute(HttpServer httpServer) throws IOException {
        SocketChannel channel = httpServer.getServerSocketChannel().accept();
        channel.configureBlocking(false);
        channel.register(httpServer.getSelector(), SelectionKey.OP_READ);
        httpServer.getSocketQueue().add(channel);
    }
}
