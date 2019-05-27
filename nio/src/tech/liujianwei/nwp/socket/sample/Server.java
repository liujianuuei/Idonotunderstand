package tech.liujianwei.nwp.socket.sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("Waiting connection from client");
        Socket clientSocket = serverSocket.accept(); // Blocked
        System.out.println("Creating I/O with client");
        DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

        do {
            System.out.println("Reading data from client");
            double length = dis.readDouble(); // Blocked
            System.out.println("The side length received is:" + length);
            double result = length * length;
            System.out.println("Writing data to client");
            dos.writeDouble(result);
            dos.flush();
            System.out.println("Waiting for next operation from client");
        } while (dis.readInt() != 0); // Blocked

        clientSocket.close();
        serverSocket.close();
    }

    private static void sleep() {
        try {
            Thread.sleep(60000);
        } catch (Exception e) {
        }
    }
}