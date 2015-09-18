package com.barabashkastuff.urldownloader.domain;

import com.barabashkastuff.urldownloader.domain.status.ImageStatus;

/**
 * Image Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public class Image {
    private String id;
    private String systemPath;
    private String url;
    private ImageStatus status;
    private String requestId;

    public Image() {
    }

    public Image(String url, String requestId) {
        this.id = "0";
        this.systemPath = "";
        this.status = ImageStatus.NON_DOWNLOADED;
        this.requestId = requestId;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemPath() {
        return systemPath;
    }

    public void setSystemPath(String systemPath) {
        this.systemPath = systemPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageStatus getStatus() {
        return status;
    }

    public void setStatus(ImageStatus status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", systemPath='" + systemPath + '\'' +
                ", url='" + url + '\'' +
                ", requestId='" + requestId + '\'' +
                ", status=" + status.getTitle() +
                '}';
    }
}
