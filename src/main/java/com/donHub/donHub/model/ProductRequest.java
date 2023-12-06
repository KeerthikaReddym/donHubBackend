package com.donHub.donHub.model;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
	public class ProductRequest {
	    private String name;
	    private String description;
	    private String emailId;
	    private double price;
	    private Category category;
	    private List<byte[]> image;
	    private Condition condition;
	    
	    @DBRef
	    private UserRequest user;
	    private LocalDateTime date;
	    
	    @Id
	    private ObjectId _id;

	    @Field("Id")
	    private Long customId;
		public void setUserRequest(UserRequest userRequest) {
			this.user = userRequest;
			// TODO Auto-generated method stub
			
		}

	 // Method to set the current date
	    public void setCurrentDate() {
	        this.date = LocalDateTime.now();
	    }
	   }
