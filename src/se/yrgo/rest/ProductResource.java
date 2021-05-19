package se.yrgo.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;
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
	public Response findById(@PathParam("productNo") int id) {
		try {
			Product result = service.getById(id);
			return Response.ok(result).build();
		} catch (ProductNotFoundException e) {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	public Product createProduct(Product product) {
		service.registerProduct(product);
		return product;
	}
}
