package com.donHub.donHub.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
public class ProductResponse implements UpdateDefinition {
	 private String name;
	    private String description;
	    private double price;
	    private Category category;
	    private List<String> image;
	    private String video;
	    private Condition condition;
		@Override
		public Boolean isIsolated() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public org.bson.Document getUpdateObject() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean modifies(String key) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void inc(String key) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public List<ArrayFilter> getArrayFilters() {
			// TODO Auto-generated method stub
			return null;
		}

}

