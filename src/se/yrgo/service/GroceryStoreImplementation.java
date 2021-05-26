package se.yrgo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import se.yrgo.dataaccess.GroceryDataAccess;
import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.dataaccess.TestingDao;
import se.yrgo.domain.Product;

@Stateless
public class GroceryStoreImplementation implements GroceryStoreService, GroceryStoreServiceLocal {

	@Inject
	// @TestingDao
	private GroceryDataAccess dao;

	/**
	 * register product
	 * 
	 * @author Tom
	 * @param product
	 */
	@Override
	public void registerProduct(Product product) {
		dao.insert(product);
	}

	/**
	 * Deletes a product given its id.
	 * 
	 * @author Emma
	 * @param id The product to be deleted.
	 */
	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		dao.deleteProduct(id);
	}

	/**
	 * get all products
	 * 
	 * @author Tom
	 */
	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {
		return dao.findAll();
	}

	/**
	 * Search for a product using its name
	 * 
	 * @author Niklas
	 * @param productName The name of the product
	 */
	@Override
	public List<Product> findByProductName(String productName) throws ProductNotFoundException {
		return dao.findByProductName(productName);
	}

	/**
	 * Find a product given an id.
	 * 
	 * @author Emma
	 * @param id The product to search for.
	 * @throws ProductNotFoundException if given an id that does not exist.
	 */
	@Override
	public Product findById(int id) throws ProductNotFoundException {
		return dao.findById(id);
	}

	/**
	 * get products between interval
	 * 
	 * @author Tom
	 * @param firstId the id of the first product you want
	 * @param secondId the id of the last product you want
	 * @return
	 */
	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) throws ProductNotFoundException {
		return dao.getAllProductsWhereIdBetween(firstId, secondId);
	}

	/**
	 * Update price on a specific product
	 * 
	 * @author Niklas
	 * @param id    The id of the product you want to change the price on
	 * @param price The price you want to change to
	 */
	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		dao.updatePrice(id, price);
	}

}
