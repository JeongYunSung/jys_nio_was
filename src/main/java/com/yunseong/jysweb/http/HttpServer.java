package com.yunseong.jysweb.http;

import com.yunseong.jysweb.http.command.KeyCommandFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class HttpServer {

    private final InetSocketAddress inetSocketAddress;
    private final HttpServerProcessor httpServerProcessor;
    private final Queue<SocketChannel> socketQueue;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public HttpServer(InetSocketAddress inetSocketAddress, HttpServerProcessor httpServerProcessor) {
        this.inetSocketAddress = inetSocketAddress;
        this.httpServerProcessor = httpServerProcessor;
        this.socketQueue = new ArrayBlockingQueue<>(1024);
    }

    public void start() {
        try (Selector selector = Selector.open()) {
            this.selector = selector;
            KeyCommandFactory keyCommandFactory = new KeyCommandFactory();
            try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
                this.serverSocketChannel = serverSocketChannel;
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                serverSocketChannel.bind(this.inetSocketAddress);
                while(true) {
                    selector.select();
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while(keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();
                        keyCommandFactory.create(key).execute(this);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocketChannel getServerSocketChannel() {
        return this.serverSocketChannel;
    }

    public HttpServerProcessor getHttpServerProcessor() {
        return this.httpServerProcessor;
    }

    public Queue<SocketChannel> getSocketQueue() {
        return this.socketQueue;
    }

    public Selector getSelector() {
        return this.selector;
    }
}
