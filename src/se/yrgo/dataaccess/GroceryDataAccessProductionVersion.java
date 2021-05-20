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

	@Override
	public void insert(Product newProduct) {
		em.persist(newProduct);
	}

	/**
	 * Deletes a product given its id.
	 * 
	 * @author Emma
	 * @param id
	 */
	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		Product p = findById(id);
		em.remove(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("select product from Product product");
		List<Product> products = q.getResultList();
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProductName(String productName) {
		Query q = em.createQuery("select product from Product product where product.productname = :productName");
		q.setParameter("productname", productName);
		return q.getResultList();
	}

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
