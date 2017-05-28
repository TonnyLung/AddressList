package com.tongxunlu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ContactController", value="/ContactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if(action.equals("add")){
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String phoneNumber = request.getParameter("phoneNumber");
			String wechat = request.getParameter("wechat");
			String workAddress = request.getParameter("workAddress");
			String homeAddress = request.getParameter("homeAddress");
			String description = request.getParameter("description");
			int count;
			try {
				Contact contact = ContactUtil.saveBean(name, sex, age, phoneNumber, wechat, 
									workAddress, homeAddress, description);
				count = ContactDAOImpl.insert(contact);
				request.setAttribute("count", count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/addContact.jsp?action=add").forward(request, response);
		} else if (action.equals("edit")) {
			String id = request.getParameter("id");
			try {
				Contact contact = ContactUtil.getBean(id);
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
			request.getRequestDispatcher("/addContact.jsp?action=edit").forward(request, response);
		} else if(action.equals("delete")) {
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
					ContactDAOImpl.delete(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				List<Contact> list = ContactDAOImpl.findAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/listContact.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equals("findAll")) {
			try {
				List<Contact> list = ContactDAOImpl.findAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/listContact.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (action.equals("save")){
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String phoneNumber = request.getParameter("phoneNumber");
			String wechat = request.getParameter("wechat");
			String workAddress = request.getParameter("workAddress");
			String homeAddress = request.getParameter("homeAddress");
			String description = request.getParameter("description");
			int count;
			try {
				Contact contact = ContactUtil.saveBean(name, sex, age, phoneNumber, wechat, 
									workAddress, homeAddress, description);
				count = ContactDAOImpl.update(contact, id);
				request.setAttribute("count", count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				List<Contact> list = ContactDAOImpl.findAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/listContact.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
