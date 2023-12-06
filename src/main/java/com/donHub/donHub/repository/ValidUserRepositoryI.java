package com.donHub.donHub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.donHub.donHub.model.ValidUser;

public interface ValidUserRepositoryI extends MongoRepository<ValidUser, String>{

    ValidUser findByEmail(String emailId);


}
