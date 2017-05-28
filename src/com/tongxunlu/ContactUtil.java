package com.tongxunlu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ContactUtil {
	public static int executeUpdate(String sql) throws SQLException {
		return executeUpdate(sql, new Object[] {});
	}
	
	public static int executeUpdate(String sql, Object...params) throws SQLException {
		Connection conn = null;
		PreparedStatement prestmt = null;
		try {
			conn = DataSource.getConnection();
			prestmt = conn.prepareStatement(sql);
			setParam(prestmt, params);
			return prestmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (prestmt != null) 
					prestmt.close();
				
				if (conn != null) {
					prestmt.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return 0;
	}
	
	public static void setParam(PreparedStatement prestmt, Object...params) 
		throws SQLException {
		for (int i = 1; i <= params.length; i ++) {
			Object param = params[i - 1];
			if (param == null) {
				prestmt.setNull(i, Types.NULL);
			} else if (param instanceof Integer) {
				prestmt.setInt(i, (int)param);
			} else if (param instanceof String) {
				prestmt.setString(i, (String)param);
			} else if (param instanceof Long) {
				prestmt.setLong(i, (Long)param);
			} else if (param instanceof Double) {
				prestmt.setDouble(i, (Double)param);
			} else if (param instanceof Date) {
				prestmt.setDate(i, (Date)param);
			} else if (param instanceof Timestamp) {
				prestmt.setTimestamp(i, (Timestamp)param);
			} else if (param instanceof Boolean) {
				prestmt.setBoolean(i, (Boolean)param);
			}
		}
	}
	
	public static int getCount(String sql) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return 0;
	}
	
	public static Contact executeQuery(Contact contact, String sql) throws SQLException {
		Connection conn = null;
		Statement prestmt = null;
		ResultSet rs = null;
		try {
			conn = DataSource.getConnection();
			prestmt = conn.createStatement();
			rs = prestmt.executeQuery(sql);
			while(rs.next()) {
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setSex(rs.getString("sex"));
				contact.setAge(rs.getInt("age"));
				contact.setPhoneNumber(rs.getString("phone_number"));
				contact.setWechat(rs.getString("wechat"));
				contact.setWorkAddress(rs.getString("work_address"));
				contact.setHomeAddress(rs.getString("home_address"));
				contact.setDescription(rs.getString("description"));
				contact.setCreateTime(rs.getTimestamp("creat_time"));
				return contact;
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (prestmt != null) prestmt.close();
				if (conn != null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return contact;
	}
	
	public static List<Contact> executeQuery(String sql) throws SQLException {
		List<Contact> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rs = null;
		try {
			conn = DataSource.getConnection();
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setSex(rs.getString("sex"));
				contact.setAge(rs.getInt("age"));
				contact.setPhoneNumber(rs.getString("phone_number"));
				contact.setWechat(rs.getString("wechat"));
				contact.setWorkAddress(rs.getString("work_address"));
				contact.setHomeAddress(rs.getString("home_address"));
				contact.setDescription(rs.getString("description"));
				contact.setCreateTime(rs.getTimestamp("creat_time"));
				list.add(contact);
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (prestmt != null) prestmt.close();
				if (conn != null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}
	
	public static Contact saveBean(String name, String sex, String age, String phoneNumber,
			String wechat, String workAddress, String homeAddress, String description) {
		Contact contact = new Contact();
		contact.setName(name);
		contact.setSex(sex);
		contact.setAge(Integer.parseInt(age));
		contact.setPhoneNumber(phoneNumber);
		contact.setWechat(wechat);
		contact.setWorkAddress(workAddress);
		contact.setHomeAddress(homeAddress);
		contact.setDescription(description);
		return contact;
	}
	
	public static Contact getBean(String id) throws NumberFormatException, SQLException {
		return ContactDAOImpl.find(Integer.parseInt(id));
	}
}
