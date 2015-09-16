package com.barabashkastuff.urldownloader.domain;

import lombok.ToString;

/**
 * Request Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@ToString
public class Request {
    private String id;
    private String url;
    private Status status;

    public Request() {
    }

    public Request(String url) {
        this.id = "0";
        this.url = url;
        this.status = Status.SUBMITTED;
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
}
