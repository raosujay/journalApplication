package com.sujay.journalApplication.Repository;

import com.sujay.journalApplication.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSentimentAnalysis() {
        Query query = new Query();
        // query.addCriteria(Criteria.where("userName").is("sujay"));
        // List<User> users = mongoTemplate.find(query, User.class);
        // return users;

        //here we are finding users who input email and there sentiment analysis
        //query.addCriteria(Criteria.where("email").exists(true).ne(null));
        //query.addCriteria(Criteria.where("sentimentAnalysis").is("true"));
        //return mongoTemplate.find(query, User.class);

        // we can also write above query as
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(
                Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"),
                Criteria.where("sentimentAnalysis").is(true)
        ));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
