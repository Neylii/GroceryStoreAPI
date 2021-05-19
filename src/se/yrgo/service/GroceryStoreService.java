package se.yrgo.service;

import java.util.List;
import javax.ejb.Remote;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;

@Remote
public interface GroceryStoreService {
	
	public void registerProduct(Product product);
	public List<Product>getAllProducts();
	public List<Product>searchByProductName(String productName);
	public Product getById(int id) throws ProductNotFoundException;
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId);
}
