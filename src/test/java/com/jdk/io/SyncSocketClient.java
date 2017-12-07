package com.jdk.io;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-03
 */
public class SyncSocketClient {

    private static int connectFlag = 0;
    private static Socket socket = null;

    public SyncSocketClient(){
        try {
            socket = new Socket("127.0.0.1", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(new Task()).start();
        SyncSocketClient syncSocketClient = new SyncSocketClient();
        syncSocketClient.sendMessage();
    }

    private static PrintWriter getWrite(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream, true);
    }

    private static BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }



    public void sendMessage() throws IOException {
        try {
            PrintWriter printWriter = getWrite(socket);
            BufferedReader bufferedReader = getReader(socket);
            BufferedReader localbuffered = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localbuffered.readLine())!=null) {
                printWriter.println(msg);
                System.out.println(bufferedReader.readLine());
                connectFlag = 0;
            }
        } finally {
            if (socket!=null) {
                socket.close();
            }
        }
    }


    private static class Task implements Runnable {
        @Override
        public void run() {
            String message = "test";
            try {
                while (true) {
                    PrintWriter printWriter = getWrite(socket);
                    BufferedReader bufferedReader = getReader(socket);
                    System.out.println("message" + message);
                    printWriter.println(message);
                    System.out.println(bufferedReader.readLine());
                    Thread.sleep(3000);
                }
            } catch (SocketException e) {
                System.out.println("服务器异常关闭");
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

}
