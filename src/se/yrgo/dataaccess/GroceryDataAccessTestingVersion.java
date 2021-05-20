package se.yrgo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import se.yrgo.domain.Product;

@Stateless
@TestingDao
public class GroceryDataAccessTestingVersion implements GroceryDataAccess {

	@Override
	public void insert(Product newProduct) {

	}

	/**
	 * @author Tom
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

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		// TODO Auto-generated method stub

	}

}