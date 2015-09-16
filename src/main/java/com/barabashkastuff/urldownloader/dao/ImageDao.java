package com.barabashkastuff.urldownloader.dao;

import com.barabashkastuff.urldownloader.domain.Image;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ResourceBundle;
import java.util.UUID;

/**
 * ImageDao Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Repository
public class ImageDao implements IImageDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ResourceBundle messages;

    @Override
    public String create(Image image) {
        if (!collectionExist()) {
            LOGGER.info(String.format(messages.getString("collection.created.log"), Image.class.getName()));
            mongoOperations.createCollection(Image.class);
        }
        String id = UUID.randomUUID().toString();
        image.setId(id);
        mongoOperations.save(image);
        LOGGER.info(String.format(messages.getString("entity.created.log"), Image.class.getName(), image.toString()));

        return id;
    }

    @Override
    public Image get(String id) {
        return null;
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Image.class);
    }
}
