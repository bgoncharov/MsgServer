package com.bgoncharov.messenger.server;

import java.net.InetAddress;

public class ServerClient {
    public String name;
    public InetAddress address;
    public int port;
    private final int ID;
    public int attemopt = 0;

    public ServerClient(String name, InetAddress address, int port, int ID){
        this.name = name;
        this.address = this.address;
        this.port = port;
        this.ID = ID;
    }
}
