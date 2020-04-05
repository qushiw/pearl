package com.jdk.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-03
 */
public class SyncSocketServer {


    public static void main(String[] args) throws IOException {
        new SyncSocketServer().reciveService();
    }



    public void reciveService() throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                BufferedReader bufferedReader = getReader(socket);
                PrintWriter printWriter = getWriter(socket);
                String msg;
                while ((msg = bufferedReader.readLine()) != null) {
                    System.out.println(msg);
                    printWriter.println("echo" + msg);
                    if ("bye".equals(msg)){
                        break;
                    }
                }

            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }


    public PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream, true);
    }

    public BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

}
