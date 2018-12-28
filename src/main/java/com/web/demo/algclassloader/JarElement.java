package com.web.demo.algclassloader;

public class JarElement {
    private String baseUrl;
    private String jarName;

    private byte[] resourceBytes;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public byte[] getResourceBytes() {
        return resourceBytes;
    }

    public void setResourceBytes(byte[] resourceBytes) {
        this.resourceBytes = resourceBytes;
    }


}
