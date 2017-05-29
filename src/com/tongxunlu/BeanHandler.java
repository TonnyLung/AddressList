package com.tongxunlu;

import java.sql.SQLException;
import java.util.List;

public class BeanHandler {
	public Contact saveBean(Contact contact, List<String> list) {
		contact.setName(list.get(0));
		contact.setSex(list.get(1));
		contact.setAge(Integer.parseInt(list.get(2)));
		contact.setPhoneNumber(list.get(3));
		contact.setWechat(list.get(4));
		contact.setWorkAddress(list.get(5));
		contact.setHomeAddress(list.get(6));
		contact.setDescription(list.get(7));
		return contact;
	}
	
	public Contact getBean(String id) throws NumberFormatException, SQLException {
		ContactDAO contactDao = new ContactDAOImpl();
		return contactDao.find(Integer.parseInt(id));
	}
}
