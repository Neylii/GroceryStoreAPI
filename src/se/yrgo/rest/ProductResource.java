package se.yrgo.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import se.yrgo.dataaccess.ProductNotFoundException;
import se.yrgo.domain.Product;
import se.yrgo.service.GroceryStoreServiceLocal;

@Stateless
@Path("/products")
public class ProductResource {
	
	@Inject
	private GroceryStoreServiceLocal service;
	
	/**
	 * @author Emma
	 * @param id
	 * @return response status
	 */
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
	
	/**
	 * @author Emma
	 * @param product
	 * @throws ProductNotFoundException
	 */
	@DELETE
	@Path("{productNo}")
	public Response deleteProduct(@PathParam("productNo") int id) {
		try {
			service.deleteProduct(id);
			return Response.status(204).build();
		} catch (ProductNotFoundException e) {
			return Response.status(404).build();
		}
	}
	
	/**
	 * 
	 * @param firstId
	 * @param secondId
	 * @return
	 * @author Tom
	 */
	@GET
	@Produces({ "application/JSON", "application/XML" })
	public Response getAllProductsWhereIdBetween(@DefaultValue("0") @QueryParam("firstId") Integer firstId,
			@QueryParam("secondId") Integer secondId) {
		if (firstId == 0 && secondId == null) {
			return Response.ok(service.getAllProducts()).build();
		}
		if (firstId != null && secondId != null) {
			return Response.ok(service.getAllProductsWhereIdBetween(firstId, secondId)).build();
		}
		return Response.status(400).build();
	}
	
	/**
	 * 
	 * @param id productID
	 * @param p Product object
	 * @return
	 * @author Niklas
	 */
	@PUT
	@Path("{productNo}")
	@Produces("application/JSON")
	@Consumes("application/JSON")
	public Response updatePrice(@PathParam("productNo") int id, Product p){
		try {
			service.updatePrice(id, p.getPrice());
			return Response.ok(service.getById(id)).build();
		} catch (ProductNotFoundException ex) {
			return Response.status(404).build();
		}
	}
}
