package com.autowebinar.core.data;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by VMoskalenko on 05.01.2017.
 */
public interface WebinarRepository extends MongoRepository<Webinar, String> {

    public Webinar findByTopic(String firstName);

}
