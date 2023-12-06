package com.donHub.donHub.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ValidUsers")
public class ValidUser {
	@Id
	  private ObjectId _id;
	  private String email;
	  private String name;
	
}
