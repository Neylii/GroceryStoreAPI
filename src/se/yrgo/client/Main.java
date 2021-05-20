package se.yrgo.client;

import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;

import se.yrgo.domain.Product;
import se.yrgo.service.GroceryStoreService;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {

	public static void main(String[] args) throws NamingException {

		Properties jndiProperties = new Properties();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi = new InitialContext(jndiProperties);

		GroceryStoreService service = (GroceryStoreService) jndi.lookup(
				"GroceryStoreAPI/GroceryStoreImplementation!se.yrgo.service.GroceryStoreService");
		
		
		Product prod1 = new Product("Apple", 6);
		Product prod2 = new Product("Banana", 9);
		Product prod3 = new Product("Chocolate", 25);
		Product prod4 = new Product("Walnuts", 35);
		Product prod5 = new Product("Tomato", 5);
		Product prod6 = new Product("Gifflar", 19);
		Product prod7 = new Product("Celsius", 21);
		Product prod8 = new Product("Milk", 11);
		Product prod9 = new Product("Almond Milk", 16);
		Product prod10 = new Product("Cheese slices", 22);
		
		service.registerProduct(prod1);
		service.registerProduct(prod2);
		service.registerProduct(prod3);
		service.registerProduct(prod4);
		service.registerProduct(prod5);
		service.registerProduct(prod6);
		service.registerProduct(prod7);
		service.registerProduct(prod8);
		service.registerProduct(prod9);
		service.registerProduct(prod10);
		
		List<Product> products = service.getAllProducts();
		for (Product product : products) {
			System.out.println(product);
		}
		
	}

}
