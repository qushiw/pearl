package com.web.restful.client;/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class Client {

    public static void main(String[] args) {
//        RestAdapter restAdapter = new RestAdapter();
//        RestService restService = restAdapter.create(RestService.class);
//        restService.getRest();
//
        RestClientInstance restClientInstance = new RestClientInstance("127.0.0.1", "8088");
        RestRequestManager restRequestManager = new RestRequestManagerImpl();
        restRequestManager.setRestInstance(restClientInstance);
        RestService restService = restRequestManager.newRestService();
        App app = restService.getRest();
        System.out.println(app.getName());
    }


}
