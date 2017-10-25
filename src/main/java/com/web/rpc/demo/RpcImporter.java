package com.web.rpc.demo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by qsfs on 2017-10-19.
 */
public class RpcImporter<S> {
    public S importer(final Class<?> serviceClass, final InetSocketAddress inetSocketAddress){

        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[] {serviceClass.getInterfaces()[0]},
                new InvocationHandler(){
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Socket socket = null;
                    ObjectInputStream objectInputStream = null;
                    ObjectOutputStream objectOutputStream = null;
                    try {
                        socket = new Socket();
                        socket.connect(inetSocketAddress);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(serviceClass.getName());
                        objectOutputStream.writeUTF(method.getName());
                        objectOutputStream.writeObject(method.getParameterTypes());
                        objectOutputStream.writeObject(args);
                        objectInputStream = new ObjectInputStream(socket.getInputStream());
                        return objectInputStream.readObject();
                    } finally {
                        if (socket != null) {
                            socket.close();
                        }

                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }

                        if (objectOutputStream != null) {
                            objectInputStream.close();
                        }
                    }
                }
            });


    }



}
