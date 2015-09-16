package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Request;

/**
 * IRequestDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public interface IRequestDao {
    public String create(Request request);
    public Request get(String id);
}
