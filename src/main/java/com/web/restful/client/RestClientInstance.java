package com.web.restful.client;/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class RestClientInstance {

    private String masterHost;
    private String masterPort;
    private RestAdapter slaveAdapter;

    private RestAdapterBuilder restAdapterBuilder;


    public RestClientInstance(String masterHost, String masterPort){
        this.masterHost = masterHost;
        this.masterPort = masterPort;

        restAdapterBuilder = new RestAdapterBuilder();
    }


    public void resetRestAdapter(){
        slaveAdapter = restAdapterBuilder.build();
    }


    public RestAdapter getSlaveAdapter() {
        return slaveAdapter;
    }
}
