package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;

/**
 * IRequestDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IRequestDao {
    public String create(Request request);
    public void updateStatus(String id, RequestStatus requestStatus);
    public Request get(String id);
}
