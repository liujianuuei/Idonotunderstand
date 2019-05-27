package tech.liujianwei.nwp.socket.b64encodec;

import tech.liujianwei.nwp.socket.b64encodec.worker.B64Worker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B64Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);

        ExecutorService exec = Executors.newFixedThreadPool(100);
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Waiting for new connection");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Accepted new connection");
                    exec.submit(new B64Worker(clientSocket));
                    System.out.println("New thread to handle request");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("B64 Server started");
    }
}