package tech.liujianwei.socket.multiple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {

        int port = 7000;
        int clientNo = 1;

        ServerSocket serverSocket = new ServerSocket(port);

        // 创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                exec.execute(new SocketServer(socket, clientNo));
                clientNo++;
            }
        } finally {
            serverSocket.close();
        }

    }
}