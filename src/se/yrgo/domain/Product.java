package se.yrgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String productName;
	private int price;
	
	public Product() {
		
	}
	
	
	public Product(String productName, int price) {
		this.productName = productName;
		this.price = price;
	}


	public int getId() {
		return Id;
	}


	public String getProductName() {
		return productName;
	}


	public int getPrice() {
		return price;
	}


	@Override
	public String toString() {
		return "Product : " + productName + ", price : " + price;
	}


	
}
