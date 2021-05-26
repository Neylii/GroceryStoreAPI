package se.yrgo.rest;

import java.util.List;

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
	 * Find a product given an id.
	 * 
	 * @author Emma
	 * @param id The product to search for.
	 * @return The product if found. Otherwise returns status 404 Not found.
	 */
	@GET
	@Produces("application/JSON")
	@Path("{productNo}")
	public Response findById(@PathParam("productNo") int id) {
		try {
			Product result = service.findById(id);
			return Response.ok(result).build();
		} catch (ProductNotFoundException e) {
			return Response.status(404).build();
		}
	}

	/**
	 * @author Tom
	 * @param product to register
	 * @return response status 200 if success. Else 400 
	 */
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
	public Response createProduct(Product product) {
		if (product.getPrice() <= 0 || product.getProductName() == null) {
			return Response.status(400).build();
		} else {
			service.registerProduct(product);
			return Response.ok(product).build();
		}
	}

	/**
	 * Deletes a product given its id.
	 * 
	 * @author Emma
	 * @param id The product to be deleted.
	 * @return Response status 204 (No Content) if success. Otherwise 404 (Not
	 *         Found).
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
	 * get products between inserted parameters (default on first is 0) if none is
	 * put in get all
	 * 
	 * @author Tom
	 * @param firstId
	 * @param secondId
	 * @return response 200. if empty 404, if nothing 400
	 */
	@GET
	@Produces({ "application/JSON", "application/XML" })
	public Response getAllProductsWhereIdBetween(@DefaultValue("0") @QueryParam("firstId") Integer firstId,
			@QueryParam("secondId") Integer secondId) {
		if (firstId == 0 && secondId == null) {
			try {
				List<Product> products = service.getAllProducts();
				return Response.ok(products).build();
			} catch (ProductNotFoundException ex) {
				return Response.status(404).build();
			}
		}
		if (firstId != null && secondId != null) {
			try {
				List<Product> products = service.getAllProductsWhereIdBetween(firstId, secondId);
				return Response.ok(products).build();
			} catch (ProductNotFoundException ex) {
				return Response.status(404).build();
			}
		}
		return Response.status(400).build();
	}

	/**
	 * Change the price of any product using the product id
	 * 
	 * @param id The id of the product that you want to change the price on
	 * @param p  Product that shall contain the new price that you want to change to
	 * @return Response status 200 if success. Otherwise 404 (Not Found).
	 * @author Niklas
	 */
	@PUT
	@Path("{productNo}")
	@Produces("application/JSON")
	@Consumes("application/JSON")
	public Response updatePrice(@PathParam("productNo") int id, Product p) {
		if (p.getPrice() <= 0) {
			return Response.status(400).build();
		}
		try {
			service.updatePrice(id, p.getPrice());
			return Response.ok(service.findById(id)).build();
		} catch (ProductNotFoundException ex) {
			return Response.status(404).build();
		}
	}
}
