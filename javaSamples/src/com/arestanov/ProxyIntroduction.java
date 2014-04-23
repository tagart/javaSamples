
package com.arestanov;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author varestanov
 */
public class ProxyIntroduction {
    public static void main(String[] args) throws InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getDeclaringClass() + " " + method.getName());
                return null;
            }
        };
        Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
        Foo f = (Foo) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
        f.do_();
    }
}
interface Foo  {
    void do_();
}
