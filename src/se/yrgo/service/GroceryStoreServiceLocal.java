package se.yrgo.service;

import java.util.List;

import javax.ejb.Local;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;


@Local
public interface GroceryStoreServiceLocal {

	public void registerProduct(Product product);
	public void deleteProduct(int id) throws ProductNotFoundException;
	public List<Product>getAllProducts();
	public List<Product>searchByProductName(String productName);
	public Product getById(int id) throws ProductNotFoundException;
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId);
	public void updatePrice(int id, int price) throws ProductNotFoundException;
}
