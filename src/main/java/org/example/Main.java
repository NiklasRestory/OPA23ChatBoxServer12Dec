package org.example;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}