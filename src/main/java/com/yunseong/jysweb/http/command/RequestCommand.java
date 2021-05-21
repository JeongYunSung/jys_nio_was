package com.yunseong.jysweb.http.command;

import com.yunseong.jysweb.http.HttpMethod;
import com.yunseong.jysweb.http.HttpServer;
import com.yunseong.jysweb.http.HttpServerRequest;
import com.yunseong.jysweb.http.HttpServerResponse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestCommand implements KeyCommand {

    @Override
    public void execute(HttpServer httpServer) throws IOException {
        try(SocketChannel channel = httpServer.getSocketQueue().poll()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            int size = channel.read(byteBuffer);
            if(size < 0) {
                return;
            }
            byte[] data = new byte[size];
            System.arraycopy(byteBuffer.array(), 0, data,0, size);

            HttpServerResponse response = new HttpServerResponse();
            httpServer.getHttpServerProcessor().process(parseRequest(data), response);
            if(response.getBody() != null)
                response.addHeader("Content-Length", String.valueOf(response.getBody().getBytes().length));

            channel.write(ByteBuffer.wrap(response.toString().getBytes()));
            channel.register(httpServer.getSelector(), SelectionKey.OP_READ);
        }
    }

    private HttpServerRequest parseRequest(byte[] data) {
        String[] result = new String(data).split("HTTP/1.1");
        String[] setting = result[0].split(" ");

        String method = setting[0];
        String[] uri = setting[1].split("\\?");
        String url = uri[0];

        Map<String, String> params = uri.length > 1 ? params = Arrays.stream(uri[1].split("&"))
                .map(elem -> elem.split("="))
                .filter(elem -> elem.length == 2)
                .collect(Collectors.toMap(e -> e[0], e -> e[1])) : new HashMap<>();

        Map<String, String> headers = Arrays.stream(result[1].replace(" ", "").split("\r\n"))
                .map(elem -> elem.split(":"))
                .filter(elem -> elem.length == 2)
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));

        return new HttpServerRequest(url, HttpMethod.valueOf(method), params, headers);
    }
}
