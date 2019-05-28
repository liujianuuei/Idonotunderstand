package tech.liujianwei.nio.nwp.nio.b64encodec.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIO {

    private SocketChannel clientChannel;

    public NIO(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    public byte[] read() throws IOException {
        LinkedList<Byte> data = new LinkedList<>();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        int size = clientChannel.read(buffer);
        while (size > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                byte b = buffer.get(); // this is in memory, should be fast.
                if (b != "\4".getBytes()[0]) { // '\4' aims to be compatible with Java I/O which is ued by client.
                    data.add(b);
                }
            }
            buffer.clear();
            size = clientChannel.read(buffer); // return 0 if no more data, so no need to check `EOT`.
        }
        return Bytes.toArray(data);
    }

    public void write(byte[]... content) throws IOException {
        byte[] data = Bytes.toArray(Bytes.toList(content));
        ByteBuffer buffer = ByteBuffer.allocate(data.length + 1);
        buffer.clear();
        buffer.put(data);
        buffer.flip();
        clientChannel.write(buffer);
    }
}
