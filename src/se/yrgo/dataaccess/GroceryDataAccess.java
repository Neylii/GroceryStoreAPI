package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Local;

import se.yrgo.domain.Product;

@Local
public interface GroceryDataAccess {

	public void insert(Product newProduct);

	public List<Product> findAll() throws ProductNotFoundException;

	public List<Product> findByProductName(String productName) throws ProductNotFoundException;
	
	public Product findById(int id) throws ProductNotFoundException;
	
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) throws ProductNotFoundException;

	public void deleteProduct(int id) throws ProductNotFoundException;

	public void updatePrice(int id, int price) throws ProductNotFoundException;
}