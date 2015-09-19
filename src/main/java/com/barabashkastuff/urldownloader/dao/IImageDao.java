package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Image;
import com.barabashkastuff.urldownloader.domain.status.ImageStatus;

/**
 * IImageDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IImageDao {
    public String create(Image image);
    public void updateStatus(String id, ImageStatus status);
    public void updatePath(String id, String path);
    public void updateSize(String id, String size);
    public void updateWidth(String id, String width);
    public void updateHeigth(String id, String heigth);
    public void updateContentType(String id, String contentType);
    public Image get(String id);
    public int removeByRequestId(String requestId);
}
