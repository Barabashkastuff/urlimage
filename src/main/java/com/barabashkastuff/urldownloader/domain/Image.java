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
    private String path;
    private ImageStatus imageStatus;
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

    public ImageStatus getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(ImageStatus imageStatus) {
        this.imageStatus = imageStatus;
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
                ", path='" + path + '\'' +
                ", requestId='" + requestId + '\'' +
                ", imageStatus=" + imageStatus.getTitle() +
                '}';
    }
}
