package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ArrayList<ClientThread> clientThreadList = new ArrayList<>();
    boolean quit = false;
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            // 10.35.35.58
            while (!quit) {
                System.out.println("Listening for client...");
                Socket client = serverSocket.accept();
                System.out.println("Found client.");
                ClientThread clientThread = new ClientThread(client, clientThreadList);
                clientThreadList.add(clientThread);
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
