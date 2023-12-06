package com.donHub.donHub.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.donHub.donHub.model.UserRequest;

public interface UserRepositoryI extends MongoRepository<UserRequest, Long>{
    UserRequest findBy_id(ObjectId _id);
    Optional<UserRequest> findBy_id_Timestamp(Long timestamp);
    UserRequest findByCustomId(Long customId);
    UserRequest findByEmailId(String emailId);
    //void updateFieldsExceptId(Long userId, UserRequest updatedUserRequest);
    



}
