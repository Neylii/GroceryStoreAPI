package se.yrgo.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import se.yrgo.domain.Product;

@Stateless
@Default
public class GroceryDataAccessProductionVersion implements GroceryDataAccess{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void insert(Product newProduct) {
		em.persist(newProduct);
		
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
	public Product findById(int id) {
		Query q = em.createQuery("select product from Product product where product.id = :id");
		q.setParameter("id", id);
		return (Product)q.getSingleResult() ;
	}
}
