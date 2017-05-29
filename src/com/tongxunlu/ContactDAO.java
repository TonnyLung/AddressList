package com.tongxunlu;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
	public int insert(Contact contact) throws SQLException;
	public int update(Contact contact, String id) throws SQLException;
	public int delete(String sql) throws SQLException;
	public Contact find(int id) throws SQLException;
	public List<Contact> findAll() throws SQLException;
}
