package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Image;

/**
 * IImageDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IImageDao {
    public String create(Image image);
    public Image get(String id);
}
