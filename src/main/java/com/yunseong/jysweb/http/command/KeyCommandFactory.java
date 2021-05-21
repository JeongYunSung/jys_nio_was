package com.yunseong.jysweb.http.command;

import java.nio.channels.SelectionKey;

public class KeyCommandFactory {

    public KeyCommand create(SelectionKey key) {
        if(!key.isValid()) {
            return new EmptyCommand();
        }
        if(key.isWritable()) {
            return new ResponseCommand();
        }
        if(key.isReadable()) {
            return new RequestCommand();
        }
        if(key.isAcceptable()) {
            return new AcceptCommand();
        }
        return new EmptyCommand();
    }
}
