package se.yrgo.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.service.GroceryStoreServiceLocal;

/**
 * Another class for GET-methods. Path is product instead of products
 * 
 * @author Niklas
 *
 */
@Stateless
@Path("/product")
public class ProductResource2 {
	@Inject
	private GroceryStoreServiceLocal service;

	@GET
	@Produces({ "application/JSON", "application/XML" })
	public Response findByProductName(@QueryParam("productName") String productName) {
		try {
			return Response.ok(service.findByProductName(productName)).build();
		} catch (ProductNotFoundException e) {
			return Response.status(404).build();
		}
	}
}