package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.domain.Image;

/**
 * IImageService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IImageService {
    String create(Image image);
    Image get(String id);
}
