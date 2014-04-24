/*
 * http://www.javaspecialists.eu/archive/Issue006.html
 */
package com.arestanov;

interface CodeInsideInterface {

    public interface Method {

        public void run(Result callback);
    }

    public interface Result {

        public void result(Object answer);

        public void exception(Exception problem);
    }
    Method calculateIQ = new Method() {
        private final java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        @Override
        public void run(Result callback) {
            int iq = 100;
            try {
                System.out.print("Do you know Java (y/n)? ");
                if ("y".equals(stdin.readLine())) {
                    iq += 20;
                }
                System.out.print("Do you know QuickBasic (y/n)? ");
                if ("y".equals(stdin.readLine())) {
                    iq += 20;
                }
                System.out.print("Do you use the Basic GOTO statement (y/n)? ");
                if ("y".equals(stdin.readLine())) {
                    iq -= 30;
                }
                System.out.print("Do you frequently use Java reflection (y/n)? ");
                if ("y".equals(stdin.readLine())) {
                    iq -= 50;
                }
                callback.result(new Integer(iq));
            } catch (java.io.IOException ex) {
                callback.exception(ex);
            }
        }
    };
}

public class CodeInsideInterfaceTest implements CodeInsideInterface {
    public static void main(String[] args) {
        CodeInsideInterfaceTest test = new CodeInsideInterfaceTest();
        test.calculateIQ.run(new CodeInsideInterface.Result() {
            @Override
            public void result(Object answer) {
                System.out.println("Your IQ is " + answer);
            }
            @Override
            public void exception(Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
