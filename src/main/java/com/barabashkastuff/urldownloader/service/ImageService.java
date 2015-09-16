package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.dao.IImageDao;
import com.barabashkastuff.urldownloader.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ImageService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageDao imageDao;

    @Override
    public String create(Image image) {
        return imageDao.create(image);
    }

    @Override
    public Image get(String id) {
        return imageDao.get(id);
    }
}
