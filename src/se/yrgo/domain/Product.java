package se.yrgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Emma
 */
@Entity
@XmlRootElement
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String productName;
	private int price;

	public Product() {
	}

	public Product(String productName, int price) {
		this.productName = productName;
		this.price = price;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
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
