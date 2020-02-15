package com.users.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import com.users.model.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoApp {

    private static final Log log = LogFactory.getLog(MongoApp.class);

    public static @Bean MongoClient getMongoClient(final String username, final String password) {
        String template = "mongodb+srv://%s:%s@resapisprinttester-tj5x1.mongodb.net/test?retryWrites=true&w=majority";
        MongoClientURI uri = new MongoClientURI(String.format(template, username, password));
        return new MongoClient(uri);
    }

}
