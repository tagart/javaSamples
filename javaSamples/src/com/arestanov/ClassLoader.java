package com.arestanov;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClassLoader extends java.lang.ClassLoader {
    private static java.lang.ClassLoader p;
    public static void main(String[] args) throws Exception {
        p = Thread.currentThread().getContextClassLoader();
        Class<?> a = new ClassLoader().loadClass("com.A", false);
        Object o = a.newInstance();
        System.out.println(a.getName());
        System.out.println(a.getField("b").get(o));
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if("java.lang.Object".equals(name)) {
            return p.loadClass(name);
        }
        System.out.println(name);
        String name_ = "C:\\users\\varestanov\\desktop\\" + name.replaceAll("\\.", "_") + ".class";
        try {
            InputStream is = new FileInputStream(name_);
            byte[] b = new byte[1000];
            int len = is.read(b);
            return super.defineClass("com.arestanov.A", b, 0, len);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name_);
        return null;
    }
}
