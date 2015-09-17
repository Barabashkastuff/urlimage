package com.barabashkastuff.urldownloader.jms;

import com.barabashkastuff.urldownloader.dao.IRequestDao;
import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * JmsRequestSender Class
 *
 * @author Andrew S. Slepakurov
 * @version 17/09/2015
 */
@Component
public class JmsRequestSender {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private IRequestDao requestDao;

    public void send(Request request) {
        jmsTemplate.convertAndSend(request);
        requestDao.updateStatus(request.getId(), RequestStatus.QUEUE);
    }
}
