package com.donHub.donHub.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class UserRequest {
	 @Id
	  private ObjectId _id;
	 @Field("Id")
	    private Long customId;

	
    private String name;
    private LocalDateTime date;
    private String emailId;
    private String password;
    private String phoneNo;
    private Boolean isAdmin;
    private List<String> product;
    private byte[] profilePic;
    
    public void setCurrentDate() {
    	this.date = LocalDateTime.now();
    }
    
      
}