package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;

@WebServlet("/ajax/json/toEng")
public class LanguageSwitcherToEng extends AbstractController {
	private static final long serialVersionUID = -3440773010973766553L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	   req.getSession().removeAttribute("language");
	   RoutingUtils.forwardToPage("products.jsp", req, resp);
	}

	
}

