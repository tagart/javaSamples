/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author varestanov
 */
public class App {
    public static void main(String[] args) {
        Digest d = new Digest1();
        byte[] b = new byte[]{(byte)10};
        new Thread(new Runnable() {

            @Override
            public void run() {
                byte[] p = d.digest(new byte[] {0, 2, 1});
                System.out.println("" + p[0] + " " + p[1] + " " + p[2]);
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                byte[] p = d.digest(new byte[] {10, 20, 30});
                System.out.println("" + p[0] + " " + p[1] + " " + p[2]);
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                byte[] p = d.digest(new byte[] {0, 2, 1});
                System.out.println("" + p[0] + " " + p[1] + " " + p[2]);
            }
        }).start();
        System.out.println(new ByteArray((byte)10,(byte)20).equals(new ByteArray((byte)10,(byte)20)));
    }
}

abstract class Digest {
      private Map<byte[], byte[]> cache = new HashMap<>();
      public byte[] digest(byte[] input) {
          byte[] result = cache.get(input);
          if (result == null) {
              synchronized (cache) {
                  result = cache.get(input);
                  if (result == null) {
                      result = doDigest(input);
                      cache.put(input, result);
                      System.out.println(cache.size());
                  }
              }
          }
          return result;
      }
 
      protected abstract byte[] doDigest(byte[] input);
  }

class Digest1 extends Digest {

    @Override
    protected byte[] doDigest(byte[] input) {
        
        return new byte[] {input[0], input[1], input[2]};
    }
    
}

class ByteArray {
    private byte[] value;
    public ByteArray(byte... values) {
        value = values;
    }
    public byte[] getValue() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof ByteArray) {
            return Arrays.equals(value, ((ByteArray)o).getValue());
        } else {
            return false;
        }        
    }
}
