
package com.arestanov;

import java.nio.charset.Charset;

public class StringConstructor {
    public static void main(String[] args) {
        byte[] b = new byte[] {(byte)0xd0,(byte)0x9f,(byte)0xd1,(byte)0x80,(byte)0xd0,(byte)0xb8,(byte)0xd0,(byte)0xb2,(byte)0xd0,(byte)0xb5,(byte)0xd1,(byte)0x82};
        String s = new String(b, Charset.forName("UTF-8"));
        System.out.println(">" + s + "<");
        b = new byte[] {(byte)0x04,(byte)0x1f,(byte)0x04,(byte)0x40,(byte)0x04,(byte)0x38,(byte)0x04,(byte)0x32,(byte)0x04,(byte)0x35,(byte)0x04,(byte)0x42};
        s = new String(b, Charset.forName("UTF-16"));
        System.out.println(">" + s + "<");
        b = new byte[] {(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x1f,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x40,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x38,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x32,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x35,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x42};
        s = new String(b, Charset.forName("UTF-32"));
        System.out.println(">" + s + "<");
        b = new byte[] {(byte)0x8f,(byte)0xe0,(byte)0xa8,(byte)0xa2,(byte)0xa5,(byte)0xe2};
        s = new String(b, Charset.forName("IBM866"));
        System.out.println(">" + s + "<");
        b = new byte[] {(byte)0xcf,(byte)0xf0,(byte)0xe8,(byte)0xe2,(byte)0xe5,(byte)0xf2};
        s = new String(b, Charset.forName("CP1251"));
        System.out.println(">" + s + "<");
        b = new byte[] {(byte)0xf0,(byte)0xd2,(byte)0xc9,(byte)0xd7,(byte)0xc5,(byte)0xd4};
        s = new String(b, Charset.forName("KOI8-R"));
        System.out.println(">" + s + "<");
    }
}
