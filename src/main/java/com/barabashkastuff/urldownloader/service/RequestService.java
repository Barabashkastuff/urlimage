package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.dao.IImageDao;
import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import com.barabashkastuff.urldownloader.worker.HtmlRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

/**
 * RequestService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Service
public class RequestService implements IRequestService {
    private static final Logger LOGGER = Logger.getLogger(HtmlRunner.class);

    @Autowired
    private IRequestDao serviceDao;
    @Autowired
    private IImageDao imageDao;
    @Autowired
    private ThreadPoolTaskExecutor asyncHtmlExecutor;
    @Autowired
    private ResourceBundle message;

    public String create(Request request) {
        String id = serviceDao.create(request);
        request.setId(id);
        asyncHtmlExecutor.submit(getHtmlRunner(request));
        LOGGER.info(message.getString(String.format("request.active.count.log", String.valueOf(asyncHtmlExecutor.getActiveCount()))));
        return id;
    }

    @Override
    public void updateStatus(String id, RequestStatus requestStatus) {
        serviceDao.updateStatus(id, requestStatus);
    }

    public Request get(String id) {
        return serviceDao.get(id);
    }

    private HtmlRunner getHtmlRunner(Request request) {
        return new HtmlRunner(serviceDao, imageDao, request, asyncHtmlExecutor);
    }
}
