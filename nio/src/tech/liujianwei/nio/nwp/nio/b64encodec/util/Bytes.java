package tech.liujianwei.nio.nwp.nio.b64encodec.util;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Bytes {
    public static byte[] toPrimitive(Byte[] wrapperCollection) {
        byte[] bytes = new byte[wrapperCollection.length];
        for (int i = 0; i < wrapperCollection.length; i++) {
            bytes[i] = wrapperCollection[i];
        }
        return bytes;
    }

    public static Byte[] toObject(byte[] primitiveCollection, int pos, int len) {
        Byte[] byteObjects = new Byte[len];
        for (int i = pos; i < len; i++) {
            byteObjects[i] = primitiveCollection[i];
        }
        return byteObjects;
    }

    public static Byte[] toObject(byte[] primitiveCollection) {
        return toObject(primitiveCollection, 0, primitiveCollection.length);
    }

    public static byte[] toArray(Collection<Byte> wrapperCollection) {
        Byte[] arr = wrapperCollection.toArray(new Byte[wrapperCollection.size()]);
        return toPrimitive(arr);
    }

    public static List<Byte> toList(byte[] src, int pos, int len) {
        return Arrays.asList(toObject(src, pos, len));
    }

    public static List<Byte> toList(byte[]... src) {
        List<Byte> list = new ArrayList<>();
        for (byte[] bs : src) {
            list.addAll(toList(bs, 0, bs.length));
        }
        return list;
    }

    public static String decode(ByteBuffer byteBuffer) throws CharacterCodingException {
        return Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
    }

    public static String toString(Collection<Byte> wrapperCollection) {
        return new String(Bytes.toArray(wrapperCollection));
    }

    public static String toString(byte[] primitiveCollection) {
        return new String(primitiveCollection);
    }
}
