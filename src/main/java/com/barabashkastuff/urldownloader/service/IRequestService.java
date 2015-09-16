package com.barabashkastuff.urldownloader.service;

import com.barabashkastuff.urldownloader.domain.Request;

/**
 * IRequestService Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IRequestService {
    String create(Request request);
    Request get(String id);
}
