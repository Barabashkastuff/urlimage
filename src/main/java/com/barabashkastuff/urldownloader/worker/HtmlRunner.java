package com.barabashkastuff.urldownloader.worker;

import com.barabashkastuff.urldownloader.dao.IImageDao;
import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Image;
import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;

/**
 * HtmlWorker Class
 *
 * @author a.slepakurov
 * @version 9/17/15
 */
public class HtmlRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(HtmlRunner.class);

    private IRequestDao requestDao;
    private IImageDao imageDao;
    private Request request;
    private ThreadPoolTaskExecutor asyncHtmlExecutor;

    public HtmlRunner(IRequestDao requestDao, IImageDao imageDao, Request request, ThreadPoolTaskExecutor asyncHtmlExecutor) {
        this.requestDao = requestDao;
        this.imageDao = imageDao;
        this.request = request;
        this.asyncHtmlExecutor = asyncHtmlExecutor;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(request.getUrl()).userAgent("Mozilla").get();
            Elements imgElements = doc.getElementsByTag("img");
            if (imgElements.size() == 0) {
                requestDao.updateStatus(request.getId(), RequestStatus.NO_IMAGE);
            } else {
                requestDao.updateImageCount(request.getId(), imgElements.size());
                for (Element element : imgElements) {
                    String imgUrl = element.attr("src");
                    Image image = new Image(imgUrl, request.getId());
                    String id = imageDao.create(image);
                    image.setId(id);
                    asyncHtmlExecutor.submit(getDownloadRunner(image));
                }
                requestDao.updateStatus(request.getId(), RequestStatus.RESOLVED);
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
            requestDao.updateStatus(request.getId(), RequestStatus.ERROR);
            throw new RuntimeException("URL can\'t be reached!");
        }
    }

    private DowloadRunner getDownloadRunner(Image image) {
        return new DowloadRunner(requestDao, imageDao, image, request);
    }
}
