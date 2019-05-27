package tech.liujianwei.nwp.socket.sample;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Connecting to server");
        Socket socket = new Socket("localhost", 7000); // No-Wait

        System.out.println("Creating I/O with server");
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);

        boolean stop = false;
        while (!stop) {
            System.out.println("Please input side length of square:");
            double length = sc.nextDouble(); // Blocked
            dos.writeDouble(length);
            dos.flush();
            double area = dis.readDouble();
            System.out.println("The area returned by server is:" + area);
            while (true) {
                System.out.println("Continue?(Y/N)");
                String opt = sc.next();
                if (opt.equalsIgnoreCase("N")) {
                    dos.writeInt(0);
                    dos.flush();
                    stop = true;
                    break;
                } else if (opt.equalsIgnoreCase("Y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }
            }
        }

        socket.close();
    }

    private static void sleep() {
        try {
            Thread.sleep(60000);
        } catch (Exception e) {
        }
    }
}