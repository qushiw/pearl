package com.web.restful.client;

/**
 * Created by jrqushiwen on 2017-12-27.
 */
public interface RestRequestManager {

    RestService newRestService();


    void setRestInstance(RestClientInstance restInstance);


}
