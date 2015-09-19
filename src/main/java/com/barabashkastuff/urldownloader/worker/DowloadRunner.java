package com.barabashkastuff.urldownloader.worker;

import com.barabashkastuff.urldownloader.dao.IImageDao;
import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Image;
import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.ImageStatus;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import org.apache.commons.io.FileUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;

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
    private Request request;

    public DowloadRunner(IRequestDao requestDao, IImageDao imageDao, Image image, Request request) {
        this.requestDao = requestDao;
        this.imageDao = imageDao;
        this.image = image;
        this.request = request;
    }

    @Override
    public void run() {
        File fileDir = new File("/tmp/imgstore/" + image.getRequestId());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        URLConnection connection = null;
        imageDao.updateStatus(image.getId(), ImageStatus.DOWNLOADING);
        try {
            URL website = new URL(image.getUrl());
            String path = "/tmp/imgstore/" + image.getRequestId() + "/" + image.getId();
            imageDao.updatePath(image.getId(), path);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(path);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            imageDao.updateStatus(image.getId(), ImageStatus.DOWNLOADED);
            File imageFile = new File(path);
            long fileSize = FileUtils.sizeOf(imageFile);
            imageDao.updateSize(image.getId(), fileSize + "");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            if (!StringUtils.hasText(image.getHeigth())) {
                imageDao.updateHeigth(image.getId(), bufferedImage.getHeight() + "");
            }
            if (!StringUtils.hasText(image.getWidth())) {
                imageDao.updateWidth(image.getId(), bufferedImage.getWidth() + "");
            }
            connection = website.openConnection();
            String contentType = connection.getContentType();
            imageDao.updateContentType(request.getId(), contentType);
        } catch (IOException e) {
            imageDao.updateStatus(image.getId(), ImageStatus.ERROR);
            throw new RuntimeException("Image can\'t be reached!");
        } finally {
            requestDao.incrementDownloadCount(request.getId());
            if (requestDao.allImagesDownloaded(request.getId())) {
                requestDao.updateStatus(request.getId(), RequestStatus.FINISHED);
            }
            try {
                rbc.close();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException("Close!");
            }
        }
    }
}
