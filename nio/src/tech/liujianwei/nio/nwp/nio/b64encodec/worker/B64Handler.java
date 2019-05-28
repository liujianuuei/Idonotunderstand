package tech.liujianwei.nio.nwp.nio.b64encodec.worker;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Base64;

public class B64Handler {
    public String toBase64(byte[] bytes) {
        System.out.println("Handling data");
        return Base64.getEncoder().encodeToString(bytes);
    }
}
