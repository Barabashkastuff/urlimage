package com.barabashkastuff.urldownloader.domain;

import com.barabashkastuff.urldownloader.domain.status.RequestStatus;

/**
 * Request Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public class Request {
    private String id;
    private String url;
    private RequestStatus status;

    public Request() {
    }

    public Request(String url) {
        this.id = "0";
        this.url = url;
        this.status = RequestStatus.SUBMITTED;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status.getTitle() +
                '}';
    }
}
