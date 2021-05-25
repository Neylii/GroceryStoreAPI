package se.yrgo.client;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import se.yrgo.domain.Product;

// Class for testing the client. Does not work if it is in the same project. 
// But works if it is moved outside.
public class TestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();

		// Create a new product.
		Product juice = new Product();
		juice.setProductName("Bravo");
		juice.setPrice(14);
		createProduct(client, juice);

		// Find all products
      		getAllProducts(client);

		// Find product with product name "Apple"
		findByProductName(client);

		// Find product with id 6.
		findById(client);

		// Finds all product between id 2 and 4.
		testInterval(client);

		// Update price on product with id 2
		int price = 999;
		updatePrice(client, price);

		// Deletes product with id 8.
		deleteProduct(client);

	}

	/**
	 * Test create products
	 * 
	 * @author Tom
	 * @param client
	 * @param product to create
	 */
	private static void createProduct(Client client, Product product) {
		Entity juiceEntity = Entity.entity(product, "application/JSON");

		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products/").request()
				.buildPost(juiceEntity).invoke();
		System.out.println(response.readEntity(Product.class).getId());
		response.close();
	}

	/**
	 * Method to test getAllProducts
	 * 
	 * @author Tom
	 * @param client
	 */
	public static void getAllProducts(Client client) {
		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products/")
				.request("application/JSON").buildGet().invoke();
		System.out.println(response.getHeaders().toString());
		System.out.println(response.getStatus());

		// get list and print it
		List<Product> products = response.readEntity(new GenericType<List<Product>>() {
		});

		for (Product product : products) {
			System.out.println(product);
		}
		response.close();
	}

	/**
	 * @author Niklas
	 * @param client
	 */
	public static void findByProductName(Client client) {
		Response response = client.target("http://localhost:8080/GroceryStore/webservice/product?productName=Apple")
				.request("application/JSON").buildGet().invoke();
		System.out.println(response.getHeaders().toString());
		System.out.println(response.getStatus());
		// System.out.println(response.readEntity(Product.class));

		// get list and print it
		List<Product> products = response.readEntity(new GenericType<List<Product>>() {
		});

		for (Product product : products) {
			System.out.println(product);
		}
		response.close();
	}

	/**
	 * Find a specific product given an id.
	 * 
	 * @author Emma
	 * @param client
	 */
	public static void findById(Client client) {
		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products/6").request()
				.buildGet().invoke();
		Product product = response.readEntity(Product.class);
		System.out.println(product);
		response.close();
	}

	/**
	 * To test Interval
	 * 
	 * @author Tom
	 * @param client
	 */
	public static void testInterval(Client client) {
		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products?firstId=2&secondId=4")
				.request("application/JSON").buildGet().invoke();
		System.out.println(response.getHeaders().toString());
		System.out.println(response.getStatus());
		// System.out.println(response.readEntity(Product.class));

		// get list and print it
		List<Product> products = response.readEntity(new GenericType<List<Product>>() {
		});

		for (Product product : products) {
			System.out.println(product);
		}
		response.close();
	}

	/**
	 * @author Niklas
	 * @param client
	 */
	public static void updatePrice(Client client, int price) {
		Product updatedProduct = new Product();
		updatedProduct.setPrice(price);

		Entity eEntity = Entity.entity(updatedProduct, "application/JSON");

		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products/2").request()
				.buildPut(eEntity).invoke();

		System.out.println("Update status was " + response.getStatus());
		System.out.println(response.readEntity(Product.class));

		response.close();
	}

	/**
	 * Deletes product given its id.
	 * 
	 * @author Emma
	 * @param client
	 */
	public static void deleteProduct(Client client) {
		Response response = client.target("http://localhost:8080/GroceryStore/webservice/products/8").request()
				.buildDelete().invoke();
		System.out.printf(
				"Trying to delete product. %nStatus: " + response.getStatus() + ", " + response.getStatusInfo());
		response.close();
	}
}
