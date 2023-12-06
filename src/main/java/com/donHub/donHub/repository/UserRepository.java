package com.donHub.donHub.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.donHub.donHub.model.UserRequest;

public class UserRepository implements UserRepositoryI {
	
	  private final MongoTemplate mongoTemplate;

	    
	    public UserRepository(MongoTemplate mongoTemplate) {
	        this.mongoTemplate = mongoTemplate;
	    }
	
	@Override
	public <S extends UserRequest> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRequest> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> S save(S entity) {
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(UserRequest entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends UserRequest> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
	    mongoTemplate.remove(new Query(), UserRequest.class);

	}

	@Override
	public List<UserRequest> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserRequest> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends UserRequest> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserRequest> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends UserRequest> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends UserRequest, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRequest findBy_id(ObjectId id) {
		return null;
	}

	


	@Override
	public UserRequest findByCustomId(Long customId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Optional<UserRequest> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(Long id) {
		 Query query = new Query(Criteria.where("Id").is(id));
	        mongoTemplate.remove(query, UserRequest.class);		
	}

	

	@Override
	public List<UserRequest> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
	
	}

	@Override
	public Optional<UserRequest> findBy_id_Timestamp(Long timestamp) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	
	@Override
	public UserRequest findByEmailId(String emailId) {
		return null;
	}
	  

}
