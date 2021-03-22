package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.Account;

@Repository
public class AccountDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Account findAccount(String userMail) throws DataAccessException {
		String SQL = "select * from Account where user_name = ?";
		Account account =  jdbcTemplate.queryForObject(SQL, new Object[] {userMail}, new AccountMapper());
		return account;
	}
}
