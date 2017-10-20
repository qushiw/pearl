package com.web.rpc.demo;

import java.net.InetSocketAddress;

/**
 * Created by qsfs on 2017-10-19.
 */
public class Client {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExporter.exporter("localhost", 8088);
                } catch (Exception e) {

                }
            }
        }).start();

        RpcImporter<EchoService> rpcImporter = new RpcImporter<EchoService>();

        EchoService echoService = rpcImporter.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));

        System.out.println(echoService.echo("are you ok?"));


    }
}
