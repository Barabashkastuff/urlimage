package com.barabashkastuff.urldownloader.domain;

import lombok.ToString;

/**
 * Image Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@ToString
public class Image {
    private String id;
    private String path;
    private String requestId;

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
