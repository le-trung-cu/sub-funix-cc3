package models;

import java.sql.Date;
import java.util.List;

public class Orders {
	private int orderId;
	private int status;
	private float price; // total amount of order
	private Date orderDate;
	private Date receiveDate;
	private String phoneNumber;
	private String address; // buyer's address
	private String userMail;
	private String discount;

	private List<ProductOrders> lp;

	public Orders() {
	}

	public Orders(String address, String userMail, String discount) {
		super();
		this.address = address;
		this.userMail = userMail;
		this.discount = discount;

		orderDate = new Date(new java.util.Date().getTime());
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public List<ProductOrders> getLp() {
		return lp;
	}

	public void setLp(List<ProductOrders> lp) {
		this.lp = lp;
	}

	public double getTotal() {
		float s = 0;
		for (ProductOrders p : lp) {
			s += p.getPrice() * p.getAmountProduct();
		}

		return Math.round(s * 100.0) / 100.0;
	}

}
