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
    String create(Request request);
    void updateStatus(String id, RequestStatus requestStatus);
    void updateImageCount(String id, int imageCount);
    void incrementDownloadCount(String id);
    boolean allImagesDownloaded(String id);
    Request get(String id);
}
