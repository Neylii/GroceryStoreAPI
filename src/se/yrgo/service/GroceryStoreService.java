package se.yrgo.service;

import java.util.List;
import javax.ejb.Remote;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;

@Remote
public interface GroceryStoreService {

	public void registerProduct(Product product);

	public void deleteProduct(int id) throws ProductNotFoundException;

	public List<Product> getAllProducts();

	public List<Product> findByProductName(String productName) throws ProductNotFoundException;

	public Product findById(int id) throws ProductNotFoundException;

	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId);

	public void updatePrice(int id, int price) throws ProductNotFoundException;
}
