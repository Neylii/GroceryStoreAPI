package se.yrgo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.GroceryDataAccess;
import se.yrgo.domain.Product;


@Stateless
public class GroceryStoreImplementation implements
		              GroceryStoreService , GroceryStoreServiceLocal {

	@Inject
	private GroceryDataAccess dao;
		
	@Override
	public void registerProduct(Product product) {
		dao.insert(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return dao.findAll();
	}

	@Override
	public List<Product> searchByProductName(String productName) {
		return dao.findByProductName(productName);
	}
	
	@Override
	public Product getById(int id) {
		return dao.findById(id);
	}
}
