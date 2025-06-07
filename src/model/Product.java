package model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name="product")
@XmlType(propOrder= {"available","wholesalerPrice","publicPrice","stock"})

public class Product {
	private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts = 0;
    
    public final static double EXPIRATION_RATE=0.60;

	public Product() {
		this.id = ++totalProducts;
    };

    
	public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
		super();
		this.id = ++totalProducts;
		this.name = name;
		this.wholesalerPrice = wholesalerPrice;
		this.publicPrice = new Amount(wholesalerPrice.getValue() * 2);
		this.available = available;
		this.stock = stock;
	}

	@XmlAttribute(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@XmlElement(name="publicPrice")
	public Amount getPublicPrice() {
		return publicPrice;
	}
	
	public void setPublicPrice(Amount publicPrice) {
		this.publicPrice = publicPrice;
	}
	
	@XmlElement(name="wholesalerPrice")
	public Amount getWholesalerPrice() {
		return wholesalerPrice;
	}
	
	public void setWholesalerPrice(Amount wholesalerPrice) {
		this.wholesalerPrice = wholesalerPrice;
	}
	
	@XmlAttribute(name="available")
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	@XmlAttribute(name="stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
		if(stock > 0) {
			this.available = true;
		}
	}

	public static int getTotalProducts() {
		return totalProducts;
	}

	public static void setTotalProducts(int totalProducts) {
		Product.totalProducts = totalProducts;
	}
	
	public void expire() {
		this.publicPrice.setValue(this.getPublicPrice().getValue()*EXPIRATION_RATE); ;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice=" + wholesalerPrice
				+ ", available=" + available + ", stock=" + stock + "totalProducts" + totalProducts +"]";
	}
}
