/*
 * http://www.javaspecialists.eu/archive/Issue004.html
 */
package com.arestanov;

import java.io.*;

class StackTrace2 {

    private static final Throwable tracer = new Throwable();
    private static final StringWriter sw = new StringWriter(1024);
    private static final PrintWriter out = new PrintWriter(sw, false);

    private StackTrace2() {
    }

    public static String getCallStack(int depth) {
        synchronized (tracer) {
            if (depth < 0) {
                throw new IllegalArgumentException();
            }
            int lineOfInterest = depth + 3;
            sw.getBuffer().setLength(0);
            tracer.fillInStackTrace();
            tracer.printStackTrace(out);
            out.flush();
            LineNumberReader in = new LineNumberReader(new StringReader(sw.toString()));
            try {
                String result;
                while((result=in.readLine())!=null) {
                    if(in.getLineNumber()==lineOfInterest) {
                        return beautify(result);
                    }
                }
            } catch(IOException ex) {}
            return null;
        }
    }
    
    private static String beautify(String raw) {
        raw = raw.trim();
        if(raw.startsWith("at ")) {
            return raw.substring(3);
        }
        return raw;
    }

}

class Log {
    public static void it(String msg) {
        String source = StackTrace2.getCallStack(1);
        System.out.println(source + " : " + msg);
    }
}

public class StackTrace2Test {
    public void f() {
        g();
    }
    public void g() {
        Log.it("where am I now?");
        System.out.println(StackTrace2.getCallStack(0));
        System.out.println(StackTrace2.getCallStack(1));
        System.out.println(StackTrace2.getCallStack(2));
        System.out.println(StackTrace2.getCallStack(3));
        System.out.println(StackTrace2.getCallStack(-1));
    }
    public static void main(String[] args) {
        new StackTrace2Test().f();
    }
}
