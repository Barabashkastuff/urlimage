package com.barabashkastuff.urldownloader.domain.rest;

import com.barabashkastuff.urldownloader.domain.Status;

/**
 * UrlRestResponse Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public class UrlRestResponse {
    private String id;
    private String url;
    private Status status;
    private String message;

    public UrlRestResponse() {
    }

    public UrlRestResponse(String id, String url, Status status, String message) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
