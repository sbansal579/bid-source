package com.realtimebidding.services;

import java.util.List;

import com.realtimebidding.model.Product;

public interface ProductService {
	
	Product getById(String id)throws Exception;
	
	Product addNewProduct(Product product)throws Exception;
	
	Product updateProduct(String id,Product product)throws Exception;
	
	Product deleteProduct(String id)throws Exception;

	List<Product> getAllProduct(); 

}
