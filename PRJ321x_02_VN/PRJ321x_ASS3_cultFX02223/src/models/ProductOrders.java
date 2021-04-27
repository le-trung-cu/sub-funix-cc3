package models;

// order detail
public class ProductOrders {
	private int orderId;
	private int productId;
	private int amountProduct; // quantity of selected product
	private float price;
	
	private String nameProduct;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAmountProduct() {
		return amountProduct;
	}
	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public double getPriceAmount() {
		return Math.round(amountProduct * price * 100.0)/100.0;
	}
}
