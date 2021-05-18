package se.yrgo.service;

import java.util.List;

import javax.ejb.Local;

import se.yrgo.domain.Product;

@Local
public interface GroceryStoreServiceLocal {

	public void registerProduct(Product product);
	public List<Product>getAllProducts ();
	public List<Product>searchByProductName(String productName);
	public Product getById(int id);
}
