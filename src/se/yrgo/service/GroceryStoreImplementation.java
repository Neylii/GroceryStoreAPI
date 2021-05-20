package se.yrgo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.GroceryDataAccess;
import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;

@Stateless
public class GroceryStoreImplementation implements GroceryStoreService, GroceryStoreServiceLocal {

	@Inject
	private GroceryDataAccess dao;

	@Override
	public void registerProduct(Product product) {
		dao.insert(product);
	}
	
	/**
	 * @author Emma
	 */
	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		dao.deleteProduct(id);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return dao.findAll();
	}

	/**
	 * @author Niklas
	 */
	@Override
	public List<Product> searchByProductName(String productName) {
		return dao.findByProductName(productName);
	}

	@Override
	public Product getById(int id) throws ProductNotFoundException {
		return dao.findById(id);
	}
	
	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) {
		return dao.getAllProductsWhereIdBetween(firstId, secondId);
	}
	
	/**
	 * @author Niklas
	 */
	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		dao.updatePrice(id, price);
	}
	
}
