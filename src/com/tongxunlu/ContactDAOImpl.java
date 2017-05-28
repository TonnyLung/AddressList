package com.tongxunlu;

import java.sql.SQLException;
import java.util.List;

public class ContactDAOImpl {
	public static int insert(Contact contact) throws SQLException {
		String sql = "insert into contact(name, sex, age, phone_number, "
			+ "wechat, work_address, home_address, description) values(?,?,?,?,?,?,?,?)";
		return ContactUtil.executeUpdate(sql, contact.getName(), contact.getSex(), contact.getAge(),
				contact.getPhoneNumber(), contact.getWechat(), contact.getWorkAddress(),
				contact.getHomeAddress(), contact.getDescription());
	}
	
	public static int update(Contact contact, String id) throws SQLException {
		String sql = "update contact set name = ?, sex = ?, age = ?, phone_number = ?, wechat = ?, "
				+ "work_address = ?, home_address = ?, description = ? where id = ?";
		return ContactUtil.executeUpdate(sql, contact.getName(), contact.getSex(), 
				contact.getAge(),contact.getPhoneNumber(), contact.getWechat(), 
				contact.getWorkAddress(),contact.getHomeAddress(), 
				contact.getDescription(), Integer.parseInt(id));
	}
	
	public static int delete(String sql) throws SQLException {
		return ContactUtil.executeUpdate(sql);
	}
	
	public static Contact find(int id) throws SQLException {
		String sql = "select * from contact where id = " + id;
		Contact contact = new Contact();
		return ContactUtil.executeQuery(contact,sql);
	}
	
	public static List<Contact> findAll() throws SQLException {
		String sql = "select * from contact";
		return ContactUtil.executeQuery(sql);
	}
}
