package tech.liujianwei.nwp.socket.b64encodec.worker;

import java.util.Base64;

public class B64Handler {
    public String toBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
