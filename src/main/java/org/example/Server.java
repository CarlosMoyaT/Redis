package org.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class Server {
    public static void main(String args[]) throws IOException {
        // create a server socket on port number 9090
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Server is running and waiting for client connection...");

            while (true) {
                // Accept incoming client connection
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected! " + clientSocket.getInetAddress());

                    // Setup input and output streams for communication with the client
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // process multiple request from client
                    while (true) {
                        String request = in.readLine();
                        if (request == null || request.equalsIgnoreCase("exit")) {
                            break;
                        }

                        // show request from client
                        System.out.println("Client says: " + request);
                        // Read message from client
                        String message = in.readLine();
                        System.out.println("Client says: " + message);
                        // Send response to the client
                        out.println("Message received by the server.");
                    }

                    // Close the client and the server socket
                    System.out.println("Closing connection with client...");
                } catch (IOException e) {
                    System.err.println("Error in client communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }
    }
}









