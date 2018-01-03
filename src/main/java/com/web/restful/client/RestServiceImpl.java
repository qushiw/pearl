package com.web.restful.client;/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class RestServiceImpl implements RestService{

    private RestClientInstance restClientInstance;
    private String name;

    public RestServiceImpl(RestClientInstance restClientInstance, String name) {
        this.restClientInstance = restClientInstance;
        this.name = name;

    }




    @Override
    public App getRest() {
        try {
            return restClientInstance.getSlaveAdapter().create(IRestService.class).getRest();
        } catch (Exception e) {
        }
        return null;
    }
}
