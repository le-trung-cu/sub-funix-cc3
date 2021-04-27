package com.entity;

public class Server {
    private final String host;
    private final int port;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Server(String hostname, int port) {
        this.host = hostname;
        this.port = port;
    }
}
