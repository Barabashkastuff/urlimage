package com.barabashkastuff.urldownloader.domain.rest;

/**
 * UrlRestRequest Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public class UrlRestRequest {
    private String url;

    public UrlRestRequest() {
    }

    public UrlRestRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}