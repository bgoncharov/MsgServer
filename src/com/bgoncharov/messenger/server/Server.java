package com.bgoncharov.messenger.server;

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
                manage();
                recieve();

            }
        }, "serverRun");

        private void manage () {
            manage = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (running) {
                        //TODO: manage the clients
                    }

                }
            });
            manage.start();

        }

        private void recieve () {

            recieve = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (running) {
                        //TODO: manage the clients
                    }

                }
            });
            recieve.start();

        }
    }
}
