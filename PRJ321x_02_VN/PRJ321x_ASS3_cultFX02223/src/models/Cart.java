package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> items; // list of item in cart

	public Cart() {
		items = new ArrayList<>();
	}

	// add a new product to cart
	public void add(Product ci) {
		for (Product p : items) {
			if (ci.getId() == p.getId()) {
				p.setNumber(p.getNumber() + 1);
				return;
			}
		}
		
		items.add(ci);
	}
	
	// subtract a exist product from cart
	public void subtract(Product ci) {
		for(Product p : items) {
			if(ci.getId() == p.getId()) {
				p.setNumber(p.getNumber() - 1);
				if(p.getNumber() <= 0) {
					items.remove(p);
				}
				return;
			}
		}
	}

	// remove a product from cart
	public void remove(int id) {
		for (Product x : items) {
			if (x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	

	// return total amount of cart
	public double getAmount() {
		double s = 0;
		for (Product x : items) {
			s += x.getNumber() * x.getPrice();
		}

		return Math.round(s * 100.0) / 100.0;
	}
	
	// return list of products in cart
	public List<Product> getItems(){
		return items;
	}
	
	public int getNumber() {
		int number = 0;
		for(Product p: items) {
			number += p.getNumber(); 
		}
		return number;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
}
