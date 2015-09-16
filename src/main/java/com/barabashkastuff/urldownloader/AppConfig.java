package com.barabashkastuff.urldownloader;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * AppConfig Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.barabashkastuff.urldownloader.*")
@PropertySource("classpath:/db.properties")
public class AppConfig implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);
    @Autowired
    private Environment environment;
    private String dbHost;
    private String dbScheme;

    @Override
    public void afterPropertiesSet() throws Exception {
        dbHost = environment.getProperty("db.host");
        dbScheme = environment.getProperty("db.scheme");
        LOGGER.info(String.format("Database connection (host: %s, scheme:%s)", dbHost, dbScheme));
    }

    @Bean
    public MongoClient mongo() throws UnknownHostException {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(200).build();
        return new MongoClient(dbHost, options);
    }

    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException {
        return new MongoTemplate(mongo(), dbScheme);
    }

    @Bean
    public ResourceBundle messages() {
        return ResourceBundle.getBundle("messages");
    }
}
