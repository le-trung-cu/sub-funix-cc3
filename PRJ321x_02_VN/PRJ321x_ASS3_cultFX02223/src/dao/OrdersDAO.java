package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.*;

public class OrdersDAO {

	// insert information of Order to data source, that including list of
	// product in cart (c) and information buyer in Orders o
	// return orderId;
	public int insertOrder(Orders o, Cart c) throws SQLException {
		PreparedStatement orderSql = null;
		PreparedStatement productOrder = null;
		try {

			DBContext db = DBContext.getIntance();
			Connection cn = db.getConnection();

			orderSql = cn.prepareStatement(
					"INSERT INTO Orders(user_mail, order_status, order_date, order_discount_code,order_address) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			orderSql.setString(1, o.getUserMail());
			orderSql.setInt(2, o.getStatus());
			orderSql.setDate(3, o.getOrderDate());
			orderSql.setString(4, o.getDiscount());
			orderSql.setString(5, o.getAddress());

			int affectedOrderRows = orderSql.executeUpdate();
			if (affectedOrderRows == 0) {
				throw new SQLException("Creating order failed, no row affected.");
			}

			try (ResultSet generatedKeys = orderSql.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int orderId = generatedKeys.getInt(1);
					o.setOrderId(orderId);

					productOrder = cn.prepareStatement(
							"INSERT INTO Orders_detail(order_id, product_id, amount_product, price_product) VALUES(?,?,?,?)");
					for (Product p : c.getItems()) {
						productOrder.setInt(1, o.getOrderId());
						productOrder.setInt(2, p.getId());
						productOrder.setInt(3, p.getNumber());
						productOrder.setFloat(4, p.getPrice());

						productOrder.addBatch();
					}
					productOrder.executeBatch();
					return orderId;
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}
		} finally {
			if(orderSql != null)
				orderSql.close();
			if(productOrder != null)
				productOrder.close();
		}

	}

	public Orders getOrder(int id) throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection cn = db.getConnection();
		String query = "SELECT o.order_id, o.order_date, o.order_address,"
				+ " d.product_id, d.amount_product, d.price_product," + " p.product_name, " + " o.user_mail"
				+ " FROM Orders AS o" + " INNER JOIN Orders_detail AS d" + " ON o.order_id = d.order_id"
				+ " INNER JOIN Products AS p" + " ON d.product_id=p.product_id" + " WHERE o.order_id=?";
		PreparedStatement orderSql = cn.prepareStatement(query);
		orderSql.setInt(1, id);

		ResultSet pointer = orderSql.executeQuery();
		boolean getedOrder = false;
		Orders orders = new Orders();
		List<ProductOrders> lp = new ArrayList<ProductOrders>();
		while (pointer.next()) {
			if (!getedOrder) {
				orders.setOrderId(pointer.getInt(1));
				orders.setOrderDate(pointer.getDate(2));
				orders.setAddress(pointer.getString(3));
				orders.setUserMail(pointer.getString(8));
				getedOrder = true;
			}

			ProductOrders product = new ProductOrders();
			product.setProductId(pointer.getInt(4));
			product.setAmountProduct(pointer.getInt(5));
			product.setPrice(pointer.getFloat(6));
			product.setNameProduct(pointer.getString(7));

			lp.add(product);
		}
		orders.setLp(lp);

		return orders;
	}

	// get count of orders
	public int getCount() throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();

		PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM Orders");
		ResultSet pointer = statement.executeQuery();
		pointer.next();
		return pointer.getInt(1);
	}

	// get count of orders
		public int getCount(String userMail) throws SQLException {
			DBContext db = DBContext.getIntance();
			Connection con = db.getConnection();

			PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM Orders WHERE user_mail=?");
			statement.setString(1, userMail);
			ResultSet pointer = statement.executeQuery();
			pointer.next();
			return pointer.getInt(1);
		}
	
	// get orders with pagination's information wrap
	public Pagination<Orders> getOrders(int currentPage, int size) throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection cn = db.getConnection();
		String query = "SELECT o.user_mail, o.order_id,  o.order_status, o.order_date, o.order_discount_code, o.order_address,"
				+ " SUM(d.amount_product * d.price_product) as total" + " FROM Orders AS o"
				+ " INNER JOIN Orders_detail AS d" + " ON o.order_id=d.order_id"
				+ " GROUP BY o.user_mail, o.order_id,  o.order_status, o.order_date, o.order_discount_code, o.order_address"
				+ " ORDER BY o.order_status, o.order_date" + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		PreparedStatement statement = cn.prepareStatement(query);
		statement.setInt(1, (currentPage - 1) * size);
		statement.setInt(2, size);

		ResultSet pointer = statement.executeQuery();
		List<Orders> orders = new ArrayList<Orders>();
		while (pointer.next()) {

			Orders o = new Orders();
			o.setUserMail(pointer.getString(1));
			o.setOrderId(pointer.getInt(2));
			o.setStatus(pointer.getInt(3));
			o.setOrderDate(pointer.getDate(4));
			o.setDiscount(pointer.getString(5));
			o.setAddress(pointer.getString(6));
			o.setPrice(pointer.getFloat(7));

			orders.add(o);
		}

		int count = getCount();

		return new Pagination<Orders>(orders, size, currentPage, count);
	}

	public Pagination<Orders> getOrders(String userMail, int currentPage, int size) throws SQLException{
		DBContext db = DBContext.getIntance();
		Connection cn = db.getConnection();
		String query = "SELECT o.user_mail, o.order_id,  o.order_status, o.order_date, o.order_discount_code, o.order_address,"
				+ " SUM(d.amount_product * d.price_product) as total" + " FROM Orders AS o"
				+ " INNER JOIN Orders_detail AS d" + " ON o.order_id=d.order_id"
				+ " WHERE o.user_mail=?"
				+ " GROUP BY o.user_mail, o.order_id,  o.order_status, o.order_date, o.order_discount_code, o.order_address"
				+ " ORDER BY o.order_status, o.order_date" + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		PreparedStatement statement = cn.prepareStatement(query);
		statement.setString(1, userMail);
		statement.setInt(2, (currentPage - 1) * size);
		statement.setInt(3, size);

		ResultSet pointer = statement.executeQuery();
		List<Orders> orders = new ArrayList<Orders>();
		while (pointer.next()) {

			Orders o = new Orders();
			o.setUserMail(pointer.getString(1));
			o.setOrderId(pointer.getInt(2));
			o.setStatus(pointer.getInt(3));
			o.setOrderDate(pointer.getDate(4));
			o.setDiscount(pointer.getString(5));
			o.setAddress(pointer.getString(6));
			o.setPrice(pointer.getFloat(7));

			orders.add(o);
		}

		int count = getCount(userMail);

		return new Pagination<Orders>(orders, size, currentPage, count);
	}
}
