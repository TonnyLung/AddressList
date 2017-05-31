package tongxunlu.controller;

import java.io.IOException;

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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		//从请求中获得action参数
		String action = request.getParameter("action");
		//根据action参数来决定将请求转发给不同的业务逻辑model去处理
		if(action.equals("add")){
			request.getRequestDispatcher("/ContactModel?action=addModel").forward(request, response);	
		} else if(action.equals("addView")) {
			request.getRequestDispatcher("/addContact.jsp").forward(request, response);
		} else if (action.equals("edit")) {
			request.getRequestDispatcher("/ContactModel?action=editModel").forward(request, response);	
		} else if(action.equals("editView")) {
			request.getRequestDispatcher("/editContact.jsp").forward(request, response);
		} else if(action.equals("delete")) {
			request.getRequestDispatcher("/ContactModel?action=deleteModel").forward(request, response);
		} else if (action.equals("findAll")) {
			request.getRequestDispatcher("/ContactModel?action=findAllModel").forward(request, response);
		} else if(action.equals("findAllView")) {
			request.getRequestDispatcher("/listContact.jsp").forward(request, response);
		} else if (action.equals("save")){
			request.getRequestDispatcher("/ContactModel?action=saveModel").forward(request, response);
		}
	}

}
