package tech.liujianwei.nwp.socket.b64encodec.worker;

import tech.liujianwei.nwp.socket.b64encodec.util.IO;

import java.io.*;
import java.net.Socket;

public class B64Worker implements Runnable {
    private Socket clientSocket;
    private B64Handler handler;

    private IO io;

    public B64Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.handler = new B64Handler();
        this.io = new IO();
    }

    @Override
    public void run() {
        try {
            InputStream is = new BufferedInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            while (true) {
                System.out.println("========= N W P =========");
                System.out.println(Thread.currentThread().getName() + " is working");
                byte[] data = io.read(is);
                dos.write(handler.toBase64(data).getBytes());
                dos.write("\4".getBytes());
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
