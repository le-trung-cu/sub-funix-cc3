package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Pagination;
import models.Product;

public class ListProductDAO {

	// return the list of products by product name
	public List<Product> search(String characters) throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();

		PreparedStatement statement = con
				.prepareStatement("SELECT * FROM Products WHERE product_name LIKE CONCAT( '%',?,'%')");
		statement.setString(1, characters);

		ResultSet pointer = statement.executeQuery();

		while (pointer.next()) {
			Product product = getProduct(pointer);
			products.add(product);
		}

		return products;
	}

	// get the product
	public Product getProduct(int id) {
		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Products WHERE product_id=?");
			statement.setInt(1, id);

			ResultSet pointer = statement.executeQuery();

			while (pointer.next()) {
				Product product = getProduct(pointer);
				return product;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get count of product
	public int getCount(String character, String brand) throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();

		PreparedStatement statement = null;
		if(brand == null || brand.isBlank()) {
			statement = con.prepareStatement("SELECT COUNT(*) FROM Products WHERE product_name LIKE CONCAT( '%',?,'%')");
			statement.setString(1, character);
		}else {
			statement = con.prepareStatement("SELECT COUNT(*) FROM Products WHERE product_brand=? AND product_name LIKE CONCAT( '%',?,'%')");
			statement.setString(1, brand);
			statement.setString(2, character);
		}
		
		
		ResultSet pointer = statement.executeQuery();
		pointer.next();
		return pointer.getInt(1);
	}

	// get products with pagination's information wrap
	public Pagination<Product> search(String character, int currentPage, int size) throws SQLException {
		return search(character, "", currentPage, size);
	}

	// get products with pagination's information wrap
	public Pagination<Product> search(String character, String brand, int currentPage, int size) throws SQLException {

		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();
		PreparedStatement statement = null;
		
		String query = "";
		if(brand == null || brand.isBlank()) {
			query = "SELECT * FROM Products" 
					+ " WHERE product_name LIKE CONCAT( '%',?,'%')"
					+ " ORDER BY product_price" 
					+ " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			statement = con.prepareStatement(query);
			statement.setString(1, character);
			statement.setInt(2, (currentPage - 1) * size);
			statement.setInt(3, size);
			
		}else {
			query = "SELECT * FROM Products"
					+ " WHERE product_brand = ? AND product_name LIKE CONCAT( '%',?,'%')"
					+ " ORDER BY product_price" 
					+ " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			statement = con.prepareStatement(query);
			statement.setString(1, brand);
			statement.setString(2, character);
			statement.setInt(3, (currentPage - 1) * size);
			statement.setInt(4, size);
		}
		
		ResultSet pointer = statement.executeQuery();
		List<Product> items = new ArrayList<Product>();
		while (pointer.next()) {
			Product p = getProduct(pointer);
			items.add(p);
		}

		int count = getCount(character, brand);

		Pagination<Product> page = new Pagination<Product>(items, size, currentPage, count);

		return page;
	}

	private Product getProduct(ResultSet point) throws SQLException {
		int id = point.getInt("product_id");
		String name = point.getString("product_name");
		String desc = point.getString("product_des");
		String src = point.getString("product_img_source");
		String type = point.getString("product_type");
		String brand = point.getString("product_brand");
		float price = point.getFloat("product_price");

		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setDesciption(desc);
		product.setPrice(price);
		product.setSrc(src);
		product.setType(type);
		product.setBrand(brand);

		return product;

	}
}
