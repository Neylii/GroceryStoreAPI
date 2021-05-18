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
		
		
		Product prod1 = new Product("Apple", 12);
		Product prod2 = new Product("Banana", 15);
		
		service.registerProduct(prod1);
		service.registerProduct(prod2);
		
		List<Product> products = service.getAllProducts();
		for (Product product : products) {
			System.out.println(product);
		}
		
	}

}
