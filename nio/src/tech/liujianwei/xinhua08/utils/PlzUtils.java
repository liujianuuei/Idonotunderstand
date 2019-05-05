package tech.liujianwei.xinhua08.utils;

import org.apache.mina.common.ByteBuffer;

public class PlzUtils {
    public static byte[] getBytesFromByteBuffer(ByteBuffer buff, int length) {
        byte[] bytes = new byte[length];
        buff.get(bytes);
        return bytes;
    }

    public static String getStringFromByteBuffer(ByteBuffer buff, int length) {
        try {
            return new String(getBytesFromByteBuffer(buff, length), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static int getIntFromByteBuffer(ByteBuffer buff, int length) {
        return Integer.parseInt(new String(getBytesFromByteBuffer(buff, length)));
    }

    public static String stringToHex(String str) {
        byte[] b = str.getBytes();
        String a = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            a = a + hex;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(stringToHex("|LN|user:liujianwei,password:123456|END|"));
    }
}
