package tongxunlu.demo;

import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;

import tongxunlu.dao.ContactDAO;
import tongxunlu.dao.ContactDAOImpl;
import tongxunlu.domain.Contact;

public class ContactDemo {
public static Logger log = Logger.getLogger(ContactDemo.class);
 	
 	Random r = new Random();
 	String[] firstNameList = {"赵", "钱", "孙", "李", "王", "刘", "肖", "张", "龙", "卞",
 			"习", "贾", "杨", "贾", "林", "陈", "吴", "罗", "彭", "马", "朱", "郑", "黄", "曾",
 			"姚", "卢", "姜", "崔 ", "钟", "谭", "陆", "汪", "范", "金", "石", "廖", "夏",
 			"韦", "傅", "白", "邹", "孟", "熊", "邱", "江", "尹", "许", "冯", "邓", "曹" };
 	String[] lastNameList = {"云飞", "涛", "明", "伟", "树同", "飞", "小兵", "国庆", "国清", "云",
 			"波", "兴义", "海", "青云", "颖", "杏", "星宇", "鑫", "馨", "芯", "淑华", "舒化", "蛋",
 			"金", "海清", "泽涛", "晓", "饶", "狗蛋", "尼玛"};
 	String[] sexList = {"男", "女"};
 	String number = "0123456789";
 	String[] hNumber = {"135", "187", "138", "177", "186", "133", "137", "183", "150", "189"};
 	String[] mNumber = {"9317", "0266", "9366", "0201", "0241", "0931", "7961", "2231", "5774", "0949"};
 
 	public void add(ContactDAO contactDao) throws SQLException {
 		log.info("开始持久化");
 		long start = System.currentTimeMillis();	
 		for (int i = 0; i < 50; i++) {
 			Contact contact = new Contact();
 			contact.setName(firstNameList[r.nextInt(50)] + 
 									lastNameList[r.nextInt(30)]);
 			contact.setAge(r.nextInt(50) + 10);
 			contact.setSex(sexList[r.nextInt(2)]);
 			contact.setPhoneNumber(hNumber[r.nextInt(10)] + mNumber[r.nextInt(10)] + 
 										number.charAt(r.nextInt(10)) + r.nextInt(1000));
 			contact.setWechat("" + r.nextInt(10000));
 			contact.setWorkAddress("上海");
 			contact.setHomeAddress("上海");
 			contact.setDescription("批量生成");
 			contactDao.insert(contact);
 		}
 		long time = System.currentTimeMillis() - start;
 		log.info("总耗时: " + time/1000);
 	}
 	
 	public static void main(String[] args) throws SQLException {
 		ContactDAO contactDao = new ContactDAOImpl();
 		ContactDemo cd = new ContactDemo();
 		log.info("进入add方法");
 		cd.add(contactDao);
 	}
}
