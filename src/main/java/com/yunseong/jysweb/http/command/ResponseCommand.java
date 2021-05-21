package com.yunseong.jysweb.http.command;

import com.yunseong.jysweb.http.HttpServer;
import com.yunseong.jysweb.http.HttpServerResponse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ResponseCommand implements KeyCommand {

    @Override
    public void execute(HttpServer httpServer) throws IOException {
        try(SocketChannel channel = httpServer.getSocketQueue().poll()) {
            HttpServerResponse response = new HttpServerResponse();
            if(response.getBody() != null)
                response.addHeader("Content-Length", String.valueOf(response.getBody().getBytes().length));

            channel.write(ByteBuffer.wrap(response.toString().getBytes()));
            channel.register(httpServer.getSelector(), SelectionKey.OP_READ);
        }
    }
}
