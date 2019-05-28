package tech.liujianwei.nio.nwp.nio.b64encodec.util;

import tech.liujianwei.nwp.socket.b64encodec.util.Bytes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IO {
    List<Byte> data;
    byte[] buffer;

    public IO() {
        data = new ArrayList<>(100 * 1024 * 1024); // 100M
        buffer = new byte[100 * 1024 * 1024]; // 100M
    }

    public byte[] read(InputStream is) throws IOException {
        return doRead(is);
    }

    private byte[] doRead(InputStream is) throws IOException {
        int len;
        System.out.println("Reading data");
        while ((len = is.read(buffer)) != -1) {
            System.out.println("Received " + len + " bytes data");
            if (buffer[len - 1] == '\4'/*EOT*/) {
                data.addAll(Bytes.toList(buffer, 0, len - 1));
                break;
            }
            data.addAll(Bytes.toList(buffer, 0, len));
        }
        System.out.println("READ DONE.");
        byte[] bytes = Bytes.toArray(data);
        data.clear();
        return bytes;
    }
}
