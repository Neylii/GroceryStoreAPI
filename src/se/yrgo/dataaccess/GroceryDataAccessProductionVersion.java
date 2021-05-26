package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import se.yrgo.domain.Product;

@Stateless
@Default
public class GroceryDataAccessProductionVersion implements GroceryDataAccess {
	@PersistenceContext
	private EntityManager em;

	/**
	 * inserts a new product
	 * 
	 * @param newProduct product to add
	 * @author Tom
	 */
	@Override
	public void insert(Product newProduct) {
		em.persist(newProduct);
	}

	/**
	 * Deletes a product given its id.
	 * 
	 * @author Emma
	 * @param id The product to be deleted.
	 */
	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		Product p = findById(id);
		em.remove(p);
	}

	/**
	 * Get all products
	 * 
	 * @author Tom
	 * @return list of all products
	 * @throws ProductNotFoundException if list is empty
	 * 
	 */
	@Override
	public List<Product> findAll() throws ProductNotFoundException {
		Query q = em.createQuery("select product from Product product");
		try {
			List<Product> products = q.getResultList();
			if (products.isEmpty()) {
				throw new ProductNotFoundException();
			}
			return products;
		} catch (NoResultException e) {
			throw new ProductNotFoundException();
		}
	}

	/**
	 * Find a product by searching for the product name
	 * 
	 * @author Niklas
	 * @param productName The name of the product
	 * @return list of products that where found
	 * @throws ProductNotFoundException
	 */
	@Override
	public List<Product> findByProductName(String productName) throws ProductNotFoundException {
		Query q = em.createQuery("select product from Product product where product.productName = :productName");
		q.setParameter("productName", productName);
		try {
			List<Product> products = q.getResultList();
			if (products.isEmpty()) {
				throw new ProductNotFoundException();
			}
			return products;
		} catch (NoResultException e) {
			throw new ProductNotFoundException();
		}
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
		Query q = em.createQuery("select product from Product product where product.id = :id");
		q.setParameter("id", id);
		try {
			return (Product) q.getSingleResult();
		} catch (NoResultException e) {
			throw new ProductNotFoundException();
		}
	}

	/**
	 * Get products between two id:s
	 * @author Tom
	 * @param firstId  the id to start from
	 * @param secondId the id to end on
	 * @return list of products,
	 * @throws ProductNotFoundException if list is empty
	 */
	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) throws ProductNotFoundException {
		Query q = em
				.createQuery("select product from Product product where product.id >= :first and product.id <=:second");
		q.setParameter("first", firstId);
		q.setParameter("second", secondId);
		try {
			List<Product> products = q.getResultList();
			if (products.isEmpty()) {
				throw new ProductNotFoundException();
			}
			return products;
		} catch (NoResultException e) {
			throw new ProductNotFoundException();
		}
	}

	/**
	 * Change the price of any product using the product id
	 * 
	 * @author Niklas
	 * @param id    The id of the product that you wanna change the price on
	 * @param price The price you want to change to
	 * @throws ProductNotFoundException if the id doesn't match an existing product
	 */
	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		Product p = findById(id);
		p.setPrice(price);
	}
}
