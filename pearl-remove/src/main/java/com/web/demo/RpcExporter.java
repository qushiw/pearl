package com.web.demo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by qsfs on 2017-10-19.
 */
public class RpcExporter {

    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostName, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName, port));

        try {
            while (true) {
                executor.execute(new ExporterTask(serverSocket.accept()));
            }
        }

        finally {
            serverSocket.close();
        }



    }


    private static class ExporterTask implements Runnable {
        Socket socket = null;

        public ExporterTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                String ClassName = objectInputStream.readUTF();
                Class<?> service = Class.forName(ClassName);
                String methodName = objectInputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[])objectInputStream.readObject();
                Object[] argments = (Object[])objectInputStream.readObject();
                Method method = service.getMethod(methodName, parameterTypes);
                Object result = method.invoke(service.newInstance(), argments);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();

                    } catch (Exception e) {

                    }
                }


                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (Exception e){

                    }
                }

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {

                    }
                }
            }



        }
    }


}
