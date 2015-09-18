package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RequestService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Service
public class RequestService implements IRequestService {

    @Autowired
    private IRequestDao serviceDao;

    public String create(Request request) {
        return serviceDao.create(request);
    }

    @Override
    public void updateStatus(String id, RequestStatus requestStatus) {
        serviceDao.updateStatus(id, requestStatus);
    }

    public Request get(String id) {
        return serviceDao.get(id);
    }
}
