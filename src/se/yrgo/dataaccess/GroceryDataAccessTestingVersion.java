package se.yrgo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import se.yrgo.domain.Product;

@Stateless
@TestingDao
public class GroceryDataAccessTestingVersion implements GroceryDataAccess {

	@Override
	public void insert(Product newProduct) {

	}

	/**
	 * find all products
	 * @author Tom
	 * @return list of all products
	 */
	@Override
	public List<Product> findAll() {
		Product prod2 = new Product("Banana", 10);
		List<Product> products = new ArrayList<Product>();
		products.add(prod2);
		return products;
	}

	@Override
	public List<Product> findByProductName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find a product given an id. 
	 * @author Emma
	 * @param id The product to search for.
	 * @return the product or null if nothing was found. 
	 */
	@Override
	public Product findById(int id) throws ProductNotFoundException {
		Product bread = new Product("Bread", 23);
		bread.setId(1);
		Product catFood = new Product("Cat food", 17);
		catFood.setId(2);
		List<Product> products = new ArrayList<Product>();
		products.add(bread);
		products.add(catFood);

		for (Product product : products) {
				if(product.getId() == id) {
					return product;
				} 
			}
		return null;
	}
	

	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @author Emma
	 */
	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		// TODO Auto-generated method stub
	}

}