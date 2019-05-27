package tech.liujianwei.nwp.socket.client;

import tech.liujianwei.nwp.socket.b64encodec.util.IO;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class Client {
    private Socket clientSocket;
    private DataOutputStream dos;
    private DataInputStream dis;

    private IO io;

    public Client() throws IOException {
        clientSocket = new Socket("127.0.0.1", 7000);
        io = new IO();
    }

    private String onMessage(byte[] bytes) throws IOException {
        output().write(bytes);
        output().write("\4".getBytes());
        output().flush();
        byte[] data = io.read(input());
        return new String(data);
    }

    private DataOutputStream output() throws IOException {
        if (dos == null) {
            dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        }
        return dos;
    }

    private DataInputStream input() throws IOException {
        if (dis == null) {
            dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        }
        return dis;
    }

    private static void sleep() {
        try {
            Thread.sleep(1 * 60 * 1000); // 1min
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(String encoded) {
        String decoded = new String(Base64.getDecoder().decode(encoded.getBytes()));
        System.out.println(decoded);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < 1000000; i++) {
                content.append("GoodLuck");
            }
            while (true) {
                String encoded = client.onMessage(content.toString().getBytes());
                print(encoded);
                sleep();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
