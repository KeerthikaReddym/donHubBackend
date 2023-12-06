package com.donHub.donHub.repository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.donHub.donHub.model.ProductRequest;

@Repository
public class ProductRepository implements ProductRepositoryI {

    private final MongoTemplate mongoTemplate;

    
    public ProductRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	/*
	 * public ProductRequest updateProduct(Long id, ProductRequest updatedProduct) {
	 * Query query = new Query(Criteria.where("Id").is(id));
	 * 
	 * Update update = new Update(); update.set("name", updatedProduct.getName());
	 * update.set("price", updatedProduct.getPrice()); // Add other fields as needed
	 * 
	 * mongoTemplate.updateFirst(query, update, ProductRequest.class);
	 * 
	 * return updatedProduct; }
	 */
	@Override
	public <S extends ProductRequest> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRequest> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(ProductRequest.class);
	}

	

	@Override
	public <S extends ProductRequest> S save(S entity) {
		//get the seller name, email id from seller table and save that in product
		
        return mongoTemplate.insert(entity);
	}

	

	@Override
	public long count() {
		// TODO Auto-generated method stub
	    return mongoTemplate.count(new Query(), ProductRequest.class);
	}

	

	@Override
	public void delete(ProductRequest entity) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void deleteAll(Iterable<? extends ProductRequest> entities) {
		
	}

	@Override
	public void deleteAll() {
	    mongoTemplate.remove(new Query(), ProductRequest.class);

		
	}

	@Override
	public List<ProductRequest> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductRequest> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends ProductRequest> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ProductRequest> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends ProductRequest> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends ProductRequest, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductRequest> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductRequest> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
	    Query query = new Query(Criteria.where("Id").is(id));
		mongoTemplate.exists(query, ProductRequest.class);
		return false;
	}

	@Override
	public void deleteById(Long id) {
	        Query query = new Query(Criteria.where("Id").is(id));
	        mongoTemplate.remove(query, ProductRequest.class);
	    }
		
	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductRequest findByCustomId(Long customId) {
	    // TODO Auto-generated method stub

	    Query query = new Query(Criteria.where("Id").is(customId));
	    return mongoTemplate.findOne(query, ProductRequest.class);
	}

	@Override
	public ProductRequest findByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
	    return mongoTemplate.findOne(query, ProductRequest.class);
	}
	@Override
	public List<ProductRequest> findByEmailId(String emailId) {
		Query query = new Query(Criteria.where("emailId").is(emailId));
	    return mongoTemplate.find(query, ProductRequest.class);
	}
	@Override
	public ProductRequest findByCondition(String condition) {
		Query query = new Query(Criteria.where("condition").is(condition));
	    return mongoTemplate.findOne(query, ProductRequest.class);
	}
	@Override
	public ProductRequest findByPrice(double price) {
		Query query = new Query(Criteria.where("price").is(price));
	    return mongoTemplate.findOne(query, ProductRequest.class);
	}

	@Override
	public ProductRequest findByCategory(String category) {
		Query query = new Query(Criteria.where("category").is(category));
	    return mongoTemplate.findOne(query, ProductRequest.class);
	}


//	/*
//	 * @Override public ProductRequest update(Long id, ProductRequest product) { //
//	 * Create a query to find the product by its ID Query query = new
//	 * Query(Criteria.where("Id").is(id));
//	 * 
//	 * // Create an update operation to set the new price ProductResponse
//	 * productResponse = new ProductResponse();
//	 * productResponse.setName(product.getName());
//	 * productResponse.setCategory(product.getCategory());
//	 * productResponse.setCondition(product.getCondition());
//	 * 
//	 * productResponse.setDescription(product.getDescription());
//	 * productResponse.setPrice(product.getPrice());
//	 * 
//	 * 
//	 * productResponse.setName(product.getName());// Perform the update operation
//	 * return null; }
//	 */

	

	

    // Implement custom queries or methods here if needed
}
