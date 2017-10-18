package com.web.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author qushiwen on 2017-09-19.
 */
public class ZookeeperClientHold {

    String leaderPath = "/qsfs/leader";

    private static CuratorFramework curatorFramework;


    public static void main(String[] args) {


        curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();


        try {
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/qsfs/qwe/qwe");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }





}
