package com.web.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author qushiwen on 2017-09-19.
 */
public class ZookeeperClientHold {

    String leaderPath = "/qsfs/leader";

    private static CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().namespace("qsw").connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    ;


    public static void main(String[] args) {
//        curatorFramework.start();
//        try {
//            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/qsfs/qwe/qwe");
//            int i=0;
//            while (i > 5) {
//                System.out.println(i++);
//            }
//            Thread.sleep(50000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//





    }





}
