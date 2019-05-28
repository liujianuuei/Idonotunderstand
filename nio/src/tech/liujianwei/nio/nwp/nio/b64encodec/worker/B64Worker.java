package tech.liujianwei.nio.nwp.nio.b64encodec.worker;

import tech.liujianwei.nio.nwp.nio.b64encodec.util.Bytes;
import tech.liujianwei.nio.nwp.nio.b64encodec.util.NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class B64Worker {
    private SocketChannel clientChannel;
    private B64Handler handler;

    private NIO nio;

    public B64Worker(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        this.handler = new B64Handler();
        this.nio = new NIO(this.clientChannel);
    }

    public void run() {
        try {
            byte[] data = nio.read();
            System.out.println("SR:" + Bytes.toString(data));
            byte[] encoded = handler.toBase64(data).getBytes(); // handler.toBase64(Bytes.decode(buffer).getBytes()).getBytes();
            nio.write(encoded, "\4".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(b);
        }
    }
}
