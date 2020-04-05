package com.web.demo.java.jmx;

public class ServerInfo implements ServerInfoMBean {


    @Override
    public String getServerInfo() {
        return "10.9.3.23";
    }

}
