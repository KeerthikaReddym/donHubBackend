package com.donHub.donHub.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.donHub.donHub.model.ProductRequest;

public interface ProductRepositoryI extends MongoRepository<ProductRequest, Long>{
    ProductRequest findByCustomId(Long customId);
    ProductRequest findByName(String name);
    ProductRequest findByCondition(String name);
    ProductRequest findByPrice(double price);
    List<ProductRequest> findByEmailId(String emailId);
    ProductRequest findByCategory(String category);
}