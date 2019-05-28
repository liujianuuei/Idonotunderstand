package tech.liujianwei.nio.nwp.nio.client;

import tech.liujianwei.nio.nwp.nio.b64encodec.util.IO;

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class Client {
    private Socket clientSocket;
    private DataOutputStream dos;
    private DataInputStream dis;

    private IO io;

    public Client(String host) throws IOException {
        clientSocket = new Socket(host, 8000);
        io = new IO();
    }

    private String onMessage(byte[] bytes) throws IOException {
        System.out.println("========= NIO =========");
        System.out.println("New message received");
        System.out.println("Send to server for b64 encoding");
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
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client(args[0]);
            while (true) {
                StringBuilder content = new StringBuilder();
                for (int i = 0; i < 1; i++) {
                    content.append(getRandomString(10));
                }
                String encoded = client.onMessage(content.toString().getBytes());
                print(encoded);
                sleep();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(String encoded) {
        String decoded = new String(Base64.getDecoder().decode(encoded.getBytes()));
        System.out.println("[" + new Date() + "] " + encoded + "(" + decoded + ")");
    }

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
