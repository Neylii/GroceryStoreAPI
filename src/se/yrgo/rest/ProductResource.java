package se.yrgo.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.yrgo.domain.Product;
import se.yrgo.service.GroceryStoreService;
import se.yrgo.service.GroceryStoreServiceLocal;

@Stateless
@Path("/products")
public class ProductResource {
	
	@Inject
	private GroceryStoreServiceLocal service;

	@GET
	@Produces("application/JSON")
	public List<Product> getAllProducts() {
	       return service.getAllProducts();
	}
	
	@GET
	@Produces("application/JSON")
	@Path("{productNo}")
	public Product findById(@PathParam("productNo") int id) {
		return service.getById(id);
	}
}
