package tongxunlu.util;

import java.sql.SQLException;
import java.util.List;

import tongxunlu.dao.ContactDAO;
import tongxunlu.dao.ContactDAOImpl;
import tongxunlu.domain.Contact;

public class BeanHandler {
	public Contact saveBean(Contact contact, List<String> list) {
		contact.setName(list.get(0));
		contact.setSex(list.get(1));
		String sage = list.get(2);
		Integer age = null;
		if (sage != null && !sage.equals("")) 
			age = Integer.parseInt(sage);
		contact.setAge(age);
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
