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
	 * @param newProduct product to add
	 * @author Tom
	 */
	@Override
	public void insert(Product newProduct) {
		em.persist(newProduct);
	}

	/**
	 * Deletes a product given its id.
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
	 * @author Tom
	 * @return list of all products
	 */
	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("select product from Product product");
		List<Product> products = q.getResultList();
		return products;
	}

	/**
	 * @author Niklas
	 */
	@Override
	public List<Product> findByProductName(String productName) {
		Query q = em.createQuery("select product from Product product where product.productname = :productName");
		q.setParameter("productname", productName);
		return q.getResultList();
	}
	
	/**
	 * Find a product given an id. 
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
	 * Get products between to id:s
	 * @author Tom
	 * @param firstId the id to start from
	 * @param secondId the id to end on
	 * @return list of products
	 */
	@Override
	public List<Product> getAllProductsWhereIdBetween(int firstId, int secondId) {
		Query q = em
				.createQuery("select product from Product product where product.id >= :first and product.id <=:second");
		q.setParameter("first", firstId);
		q.setParameter("second", secondId);
		List<Product> products = q.getResultList();
		return products;
	}

	/**
	 * @author Niklas
	 * @param id
	 * @param price
	 * @throws ProductNotFoundException
	 */
	@Override
	public void updatePrice(int id, int price) throws ProductNotFoundException {
		Product p = findById(id);
		p.setPrice(price);
	}
}
