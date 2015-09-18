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
    public Image get(String id);
}
