package com.bgoncharov.messenger.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ServerClient> clients = new ArrayList<ServerClient>();
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
                System.out.println("Server started on port: " + port + ". Enjoy!");
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
                        process(packet);
                        clients.add(new ServerClient("Jones", packet.getAddress(), packet.getPort(), 2323));
                        System.out.println(clients.get(0).address.toString() + ":" + clients.get(0).port);
                    }
                }
            }, "recieve");
            recieve.start();
        }

        private void process(DatagramPacket packet){
            String str = new String(packet.getData());
            if(str.startsWith("/c/")) {
                clients.add(new ServerClient(str.substring(3, str.length()), packet.getAddress(), packet.getPort(), 2323));
                System.out.println(str.substring(3, str.length()));
            } else  {
                System.out.println(str);
            }
        }
    }
