package se.yrgo.client;

import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;

import se.yrgo.domain.Product;
import se.yrgo.service.GroceryStoreService;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Niklas
 */
public class Main {

	public static void main(String[] args) throws NamingException {

		Properties jndiProperties = new Properties();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi = new InitialContext(jndiProperties);

		GroceryStoreService service = (GroceryStoreService) jndi.lookup(
				"GroceryStoreAPI/GroceryStoreImplementation!se.yrgo.service.GroceryStoreService");
		
		Product apple = new Product("Apple", 6);
		Product banana = new Product("Banana", 9);
		Product chocolate = new Product("Chocolate", 25);
		Product walnuts = new Product("Walnuts", 35);
		Product tomato = new Product("Tomato", 5);
		Product gifflar = new Product("Gifflar", 19);
		Product celsius = new Product("Celsius", 21);
		Product milk = new Product("Milk", 11);
		Product almondMilk = new Product("Almond Milk", 16);
		Product cheese = new Product("Cheese", 62);
		
		service.registerProduct(apple);
		service.registerProduct(banana);
		service.registerProduct(chocolate);
		service.registerProduct(walnuts);
		service.registerProduct(tomato);
		service.registerProduct(gifflar);
		service.registerProduct(celsius);
		service.registerProduct(milk);
		service.registerProduct(almondMilk);
		service.registerProduct(cheese);
		
		List<Product> products = service.getAllProducts();
		for (Product product : products) {
			System.out.println(product);
		}
		
	}

}
