package com.web.restful.client;/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class RestRequestManagerImpl implements RestRequestManager {

    private RestClientInstance restClientInstance;

    @Override
    public RestService newRestService() {
        restClientInstance.resetRestAdapter();
        RestAdapter restAdapter = restClientInstance.getSlaveAdapter();
        return restAdapter.create(RestService.class);
    }

    @Override
    public void setRestInstance(RestClientInstance restInstance) {
        this.restClientInstance = restInstance;
    }

}
