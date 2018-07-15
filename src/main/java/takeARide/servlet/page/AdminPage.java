package takeARide.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.utils.RoutingUtils;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/admin", initParams={
		@WebInitParam(name="login", value="admin"),
		@WebInitParam(name="password", value="password")
})
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 2232133274387435743L;
	private String login;
	private String password;
	@Override
	public void init() throws ServletException {
		login = getServletConfig().getInitParameter("login");
		password = getServletConfig().getInitParameter("password");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
	
			if(validate(login, password)) {
				RoutingUtils.forwardToPage("AdminPage.jsp", req, resp);
			}else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().println("FAILED");
			}	
	}
	
	private boolean validate(String login, String password) {
		StringBuilder errors = new StringBuilder();
		
		if(this.login.equals(login) && this.password.equals(password)) {
			System.out.println("Login via login and password: "+login+"/"+password);
			return true;
		} else {
			errors.append(String.format("Invalid login and(or) password: %s/%s\n", login, password));
			return false;
		}
	}
}