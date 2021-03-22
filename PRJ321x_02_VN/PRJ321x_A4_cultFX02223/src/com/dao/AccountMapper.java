package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Account;

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int arg1) throws SQLException {
		Account account = new Account();
		account.setUsr(rs.getString("user_mail"));
		account.setRole(rs.getInt("account_role"));
		account.setName(rs.getString("user_name"));
		account.setPwd(rs.getString("password"));
		account.setPhone(rs.getString("user_phone"));
		account.setAddress(rs.getString("user_address"));
		return account;
	}

}
