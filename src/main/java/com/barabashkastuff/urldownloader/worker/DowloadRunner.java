package com.barabashkastuff.urldownloader.worker;

import com.barabashkastuff.urldownloader.dao.IImageDao;
import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Image;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * DowloadRunner Class
 *
 * @author a.slepakurov
 * @version 9/17/15
 */
public class DowloadRunner implements Runnable {
    private IRequestDao requestDao;
    private IImageDao imageDao;
    private Image image;

    public DowloadRunner(IRequestDao requestDao, IImageDao imageDao, Image image) {
        this.requestDao = requestDao;
        this.imageDao = imageDao;
        this.image = image;
    }

    @Override
    public void run() {
        File fileDir = new File("/opt/imgstore/" + image.getRequestId());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            URL website = new URL(image.getUrl());
            imageDao.updatePath(image.getId(), image.getSystemPath());
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream("/opt/imgstore/" + image.getRequestId() + "/" + image.getId());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (IOException e) {
            requestDao.updateStatus(image.getId(), RequestStatus.ERROR);
            throw new RuntimeException("Image can\'t be reached!");
        } finally {
            try {
                rbc.close();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException("Close!");
            }
        }
    }
}
