package com.bgoncharov.messenger.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    private int port;
    private DatagramSocket socket;
    private Thread serverRun, manage, recieve;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        serverRun = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                System.out.println("Server started on port: " + port);
                manage();
                recieve();
            }
        }, "serverRun");
        serverRun.start();
    }

        private void manage () {
            manage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        //TODO: manage the clients
                    }
                }
            }, "manage");
            manage.start();
        }

        private void recieve () {
            recieve = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        try {
                            socket.receive(packet);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String str = new String(packet.getData());
                        System.out.println(str);
                    }
                }
            }, "recieve");
            recieve.start();
        }
    }
