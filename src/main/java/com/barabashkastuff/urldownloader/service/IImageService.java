package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.domain.Image;
import com.barabashkastuff.urldownloader.domain.status.ImageStatus;

/**
 * IImageService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IImageService {
    String create(Image image);
    void updateStatus(String id, ImageStatus status);
    Image get(String id);
}
