package se.yrgo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import se.yrgo.domain.Product;

@Stateless
@TestingDao
public class GroceryDataAccessTestingVersion implements GroceryDataAccess{

	@Override
	public void insert(Product newProduct) {

	}

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


	
}
