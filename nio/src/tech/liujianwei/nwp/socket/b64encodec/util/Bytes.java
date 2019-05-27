package tech.liujianwei.nwp.socket.b64encodec.util;

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

    public static List<Byte> toList(byte[] src) {
        return toList(src, 0, src.length);
    }
}
