package com.donHub.donHub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donHub.donHub.model.Category;
import com.donHub.donHub.model.Condition;
import com.donHub.donHub.model.ProductRequest;
import com.donHub.donHub.service.ProductServiceI;

@RestController
@RequestMapping("/donHub/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	ProductServiceI productServiceI;

	/**
	 * 
	 * @return Add data in Product table
	 */

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProductRequest> addProduct(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") Double price,
			@RequestParam("category") Category category, @RequestParam("condition") Condition condition,

			@RequestParam("emailId") String emailId,
			@RequestParam("images") MultipartFile[] images) {


		ProductRequest productRequest = new ProductRequest();
		productRequest.setName(name);
		productRequest.setDescription(description);
		productRequest.setPrice(price);
		productRequest.setCategory(category);
		productRequest.setCondition(condition); // Assuming Condition is an enum
		productRequest.setEmailId(emailId);

		List<byte[]> productImages = new ArrayList<>();
		for (MultipartFile file : images) {
			try {
				productImages.add(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace(); // Handle IOException, maybe return a bad request
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		productRequest.setImage(productImages);

		return new ResponseEntity<>(productServiceI.addProduct(productRequest), HttpStatus.OK);
	}

	private Condition convertStringToCondition(String conditionStr) {
		String formattedCondition = conditionStr.replace(" ", "_").toUpperCase();
		try {
			return Condition.valueOf(formattedCondition);
		} catch (IllegalArgumentException e) {
			// Handle the case where the formatted string does not match any enum constant
			throw new IllegalArgumentException("Invalid condition value: " + conditionStr);
		}
	}

	private Category convertStringToCategory(String categoryStr) {
		String formattedCategory = categoryStr.replace(" ", "_").toUpperCase();
		try {
			return Category.valueOf(formattedCategory);
		} catch (IllegalArgumentException e) {
			// Handle the case where the formatted string does not match any enum constant
			throw new IllegalArgumentException("Invalid category value: " + categoryStr);
		}
	}

	/*
	 * @GetMapping public ResponseEntity<ProductRequest> updateProduct(@RequestBody
	 * ProductRequest productRequest) {
	 * 
	 * return new ResponseEntity<>(productServiceI.updateProduct(productRequest),
	 * HttpStatus.OK); }
	 */

	@GetMapping("/pro/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		ProductRequest product = productServiceI.getProductById(id);
		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found!");

	}
	/*
	 * @PutMapping("/{id}") public ResponseEntity<?> updateProduct(@PathVariable
	 * Long id, @RequestBody ProductRequest productRequest){ ProductRequest product
	 * = productServiceI.updateProduct(id, productRequest); return product != null ?
	 * ResponseEntity.status(HttpStatus.OK).body(product) :
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found!");
	 * 
	 * }
	 */

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
		Boolean product = productServiceI.updateProduct(id, productRequest);
		return product? ResponseEntity.status(HttpStatus.OK).body("Updated successfully")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found!");

	}

	@GetMapping()
	public ResponseEntity<List<ProductRequest>> getProducts() {

		return new ResponseEntity<>(productServiceI.getProducts(), HttpStatus.OK);
	}

	@GetMapping({"getByName/{name}" })
	public ResponseEntity<List<ProductRequest>> getProductByName(@PathVariable String name) {
		List<ProductRequest> product = productServiceI.getProductByName(name);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping({"/getProductsByFilters/{category}/{condition}"})
	public ResponseEntity<List<ProductRequest>> getProductsByFilters(@PathVariable Category category, @PathVariable Condition condition) {
		List<ProductRequest> product = productServiceI.getProductsByFIlters(category, condition);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	@GetMapping({ "getByCondition/{condition}" })
	public ResponseEntity<List<ProductRequest>> getProductByCondition(@PathVariable String condition) {
		List<ProductRequest> product = productServiceI.getProductByCondition(condition);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping({ "LowestgetByPrice/{price}" })
	public ResponseEntity<List<ProductRequest>> getProductByPriceLow(@PathVariable double price) {
		List<ProductRequest> product = productServiceI.getProductByPriceLow(price);

		if (product != null)
			return ResponseEntity.status(HttpStatus.OK).body(product);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	@GetMapping({"HighestgetByPrice/{price}" })
	public ResponseEntity<List<ProductRequest>> getProductByPriceHigh(@PathVariable double price) {
		List<ProductRequest> product = productServiceI.getProductByPriceHigh(price);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping({ "getByEmail/{emailId}" })
	public ResponseEntity<?> getProductByEmail(@PathVariable String emailId) {
		List<ProductRequest> product = productServiceI.getProductByEmail(emailId);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found!");
	}

	@GetMapping({ "getByCategory/{category}" })
	public ResponseEntity<?> getProductByCategory(@PathVariable String category) {
		List<ProductRequest> product = productServiceI.getProductByCategory(category);

		return product != null ? ResponseEntity.status(HttpStatus.OK).body(product)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	 	 @DeleteMapping() 
	 	 public ResponseEntity<String> deleteAll() {
	  return productServiceI.deleteAllProducts() ?
	  ResponseEntity.status(HttpStatus.OK).body("successfully deleted everything")
	  : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found!"); }
	 

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		return productServiceI.deleteById(id) == true
				? ResponseEntity.status(HttpStatus.OK).body("successfully deleted record")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found!");
	}

}