package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import models.Account;
import models.Pagination;

public class AccountDAO {

	public int createUser(Account acc) throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection cn = db.getConnection();

		String query = "INSERT INTO Account(user_mail, password, account_role, user_name, user_address, user_phone)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = cn.prepareStatement(query);

		statement.setString(1, acc.getUsr());
		statement.setString(2, acc.getPwd());
		statement.setInt(3, acc.getRole());
		statement.setString(4, acc.getName());
		statement.setString(5, acc.getAddress());
		statement.setString(6, acc.getPhone());

		return statement.executeUpdate();
	}

	// get count of account
	public int getCount() throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection con = db.getConnection();

		PreparedStatement statement = con
				.prepareStatement("SELECT COUNT(*) FROM Account");

		ResultSet pointer = statement.executeQuery();
		pointer.next();
		return pointer.getInt(1);
	}

	public Pagination<Account> getUsers(int currentPage, int size) throws SQLException {
		DBContext db =  DBContext.getIntance();
		Connection con = db.getConnection();
		String query = "SELECT user_mail, password, account_role, user_name, user_address, user_phone" + " FROM Account"
				+ " ORDER BY user_mail" + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		PreparedStatement statement = con.prepareStatement(query);
		statement.setInt(1, (currentPage - 1) * size);
		statement.setInt(2, size);
		
		ResultSet pointer = statement.executeQuery();
		List<Account> items = new ArrayList<>();
		
		while(pointer.next()) {
			String mail = pointer.getString(1);
			String pass = pointer.getString(2);
			int role = pointer.getInt(3);
			String name = pointer.getString(4);
			String address = pointer.getString(5);
			String phone = pointer.getString(6);
			
			Account acc = new Account(mail, pass, role, name, address, phone, 0);
			items.add(acc);
		}
		int count = getCount();
		return new Pagination<Account>(items, size, currentPage, count);
	}

	public Account findAccount(String mail, String pass) throws SQLException {
		DBContext db = DBContext.getIntance();
		Connection cn = db.getConnection();
		String query = "SELECT user_mail, password, account_role, user_name, user_address, user_phone"
				+ " FROM Account"
				+ " WHERE user_mail=? AND password=?";
		PreparedStatement statement = cn.prepareStatement(query);
		statement.setString(1, mail);
		statement.setString(2, pass);
		
		ResultSet pointer = statement.executeQuery();
		while(pointer.next()) {
			String usr = pointer.getString(1);
			String pwd = pointer.getString(2);
			int role = pointer.getInt(3);
			String name = pointer.getString(4);
			String address = pointer.getString(5);
			String phone = pointer.getString(6);
			Account acc = new Account(usr, pwd, role, name, address, phone, 0);
			
			return acc;
		}
		
		return null;
		
	}
}
