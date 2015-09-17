package com.barabashkastuff.urldownloader.domain.rest;

import com.barabashkastuff.urldownloader.domain.status.RequestStatus;

/**
 * UrlRestResponse Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public class UrlRestResponse {
    private String id;
    private String url;
    private RequestStatus requestStatus;
    private String message;

    public UrlRestResponse() {
    }

    public UrlRestResponse(String id, String url, RequestStatus requestStatus, String message) {
        this.id = id;
        this.url = url;
        this.requestStatus = requestStatus;
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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
