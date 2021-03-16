package models;

public class Product {
	private String id;
	private String name;
	private String price;
	private String delPrice;
	private String picture;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDelPrice() {
		return delPrice;
	}
	public void setDelPrice(String delPrice) {
		this.delPrice = delPrice;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
