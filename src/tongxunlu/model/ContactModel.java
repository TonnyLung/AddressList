package tongxunlu.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tongxunlu.dao.ContactDAO;
import tongxunlu.dao.ContactDAOImpl;
import tongxunlu.domain.Contact;
import tongxunlu.util.BeanHandler;
import tongxunlu.util.ContactUtil;


@WebServlet(name="ContactModel", value="/ContactModel")
public class ContactModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactModel() {
    	 super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = Logger.getLogger("ContactMolde");
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if(action.equals("addModel")){
			List<String> list = new ArrayList<>();
			//将从前端表单获得的请求放入容器中
			list.add(request.getParameter("name"));
			list.add(request.getParameter("sex"));
			list.add(request.getParameter("age"));
			list.add(request.getParameter("phoneNumber"));
			list.add(request.getParameter("wechat"));
			list.add(request.getParameter("workAddress"));
			list.add(request.getParameter("homeAddress"));
			list.add(request.getParameter("description"));
			System.out.println(request.getParameter("name"));
			//将参数交给BeanHanlder去生成Bean对象,利用面向接口设计
			BeanHandler beanHandler = new BeanHandler();
			Contact contact = new Contact();
			contact = beanHandler.saveBean(contact, list);
			//将对象contact进行持久化,调用持久化逻辑处理
			ContactDAO contactDao = new ContactDAOImpl();
			try {
				int count = contactDao.insert(contact);
				request.setAttribute("count", count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//将处理后的数据结果和需要展现的逻辑视图以及请求转发给控制器
			request.getRequestDispatcher("/ContactController?action=addView").forward(request, response);
		} else if(action.equals("editModel")) {
			String id = request.getParameter("id");
			BeanHandler beanHandler = new BeanHandler();
			Contact contact;
			try {
				contact = beanHandler.getBean(id);
				request.setAttribute("id", id);
				request.setAttribute("name", contact.getName());
				request.setAttribute("sex", contact.getSex());
				String age = "";
				age += age + contact.getAge();
				request.setAttribute("age", age);
				request.setAttribute("phoneNumber", contact.getPhoneNumber());
				request.setAttribute("wechat", contact.getWechat());
				request.setAttribute("workAddress", contact.getWorkAddress());
				request.setAttribute("homeAddress", contact.getHomeAddress());
				request.setAttribute("description", contact.getDescription());
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/ContactController?action=editView").forward(request, response);
		} else if(action.equals("deleteModel")) {
			String [] id = request.getParameterValues("id");
			String sid = "";
			if(id == null || id.length == 0) {
				String message = "请选择一个要删除的项目！";
				request.setAttribute("message", message);
			} else {
				for(int i = 0; i < id.length; i++){
					if(i == 0) {
						sid = "" + id[i];
					} else {
						sid += "," + id[i];
					}
				}
				String sql = "delete from contact where id in (" + sid + ")";
				try {
					ContactDAO contactDAO = new ContactDAOImpl();
					int count = contactDAO.delete(sql);
					request.setAttribute("count", count);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("/ContactController?action=findAll").forward(request, response);
			}
		} else if(action.equals("findAllModel")) {
			
			try {
				String sql = "select count(*) from contact";
				int recordCount = ContactUtil.getCount(sql);		//通过查询获得的总记录数
				int pageSize = 10;      			//一页显示10条记录
				int pageNum = 1;					//当前页面
				int pageCount = 1;					//初始化总页面
				pageCount = (recordCount + pageSize - 1) / pageSize;   //计算总页面
				String sPageNum = request.getParameter("pageNum");
				if (sPageNum != null && !sPageNum.equals("")){
					pageNum = Integer.parseInt(sPageNum);
				} else {
					pageNum = 1;
				}
				log.info("pageNum: " + pageNum);
				String pageUrl = request.getRequestURI();
				pageUrl = pageUrl + "?action=findAllModel";
				log.info("pageUrl: " + pageUrl);
				int startRecord = (pageNum -1) * pageSize;
				ContactDAO contactDAO = new ContactDAOImpl();
				List<Contact> list = contactDAO.findAll(startRecord, pageSize);
				request.setAttribute("list", list);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("recordCount", recordCount);
				request.setAttribute("pageUrl", pageUrl);
				request.getRequestDispatcher("/ContactController?action=findAllView").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(action.equals("saveModel")) {
			List<String> list = new ArrayList<>();
			String id = request.getParameter("id");
			list.add(request.getParameter("name"));
			list.add(request.getParameter("sex"));
			list.add(request.getParameter("age"));
			list.add(request.getParameter("phoneNumber"));
			list.add(request.getParameter("wechat"));
			list.add(request.getParameter("workAddress"));
			list.add(request.getParameter("homeAddress"));
			list.add(request.getParameter("description"));
			int count;
			try {
				BeanHandler beanHandler = new BeanHandler();
				Contact contact = new Contact();
				contact = beanHandler.saveBean(contact, list);
				ContactDAO contactDAO = new ContactDAOImpl();
				count = contactDAO.update(contact, id);
				request.setAttribute("count", count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/ContactController?action=findAll").forward(request, response);
		}
	}

}
