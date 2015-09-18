package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;

/**
 * IRequestService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IRequestService {
    String create(Request request);
    void updateStatus(String id, RequestStatus requestStatus);
    Request get(String id);
}
