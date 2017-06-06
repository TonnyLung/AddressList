package tongxunlu;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

@WebServlet(name="Log4jInit", value="/Log4jInit")
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Log4jInit() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j-init-file");
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
		}
	}

	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
