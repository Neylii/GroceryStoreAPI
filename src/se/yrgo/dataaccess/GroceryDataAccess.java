package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Local;

import se.yrgo.domain.Product;

@Local
public interface GroceryDataAccess {

	public void insert(Product newProduct);

	public List<Product> findAll();

	public List<Product> findByProductName(String productName);
	
	public Product findById(int id) throws ProductNotFoundException;
	
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId);
}