package tech.liujianwei.nwp.socket.b64encodec.worker;

import java.util.Base64;

public class B64Handler {
    public String toBase64(byte[] bytes) {
        System.out.println("Handling data");
        return Base64.getEncoder().encodeToString(bytes);
    }
}
