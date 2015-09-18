package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ResourceBundle;
import java.util.UUID;

/**
 * RequestDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Repository
public class RequestDao implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ResourceBundle messages;

    @Override
    public String create(Request request) {
        if (!collectionExist()) {
            LOGGER.info(String.format(messages.getString("collection.created.log"), Request.class.getName()));
            mongoOperations.createCollection(Request.class);
        }
        String id = UUID.randomUUID().toString();
        request.setId(id);
        mongoOperations.save(request);
        LOGGER.info(String.format(messages.getString("entity.created.log"), Request.class.getName(), request.toString()));
        return id;
    }

    @Override
    public void updateStatus(String id, RequestStatus requestStatus) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoOperations.updateFirst(query, Update.update("status", requestStatus), Request.class);
        LOGGER.info(String.format("Request status update for id=%s, status=%s", id, requestStatus.getTitle()));
    }

    @Override
    public Request get(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Request.class);
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Request.class);
    }
}
