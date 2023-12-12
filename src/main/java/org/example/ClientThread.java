package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {

    ArrayList<ClientThread> clientThreadList;
    BufferedWriter writer;
    BufferedReader reader;

    public ClientThread(Socket clientSocket, ArrayList<ClientThread> clientThreadList) {
        this.clientThreadList = clientThreadList;
        try {
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            reader = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            writer = new BufferedWriter(osw);

            this.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Listening for message.");
                String message = reader.readLine();
                System.out.println("Got " + message);

                for (ClientThread client : clientThreadList) {
                    if (client != this) {
                        client.send(message);
                    }
                }

            }
        } catch (IOException e) {
            // Write code for when we lose the connection here.
            System.out.println(e.getMessage());
        }
    }

    private void send(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
