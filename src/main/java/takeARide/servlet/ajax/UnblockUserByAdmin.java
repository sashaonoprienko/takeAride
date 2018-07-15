package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/product/UnblockByadmin")
public class UnblockUserByAdmin extends AbstractController  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6003653225276575702L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getOrderService().UnblockUser(req.getParameter("useremail"));
		
		req.getSession().setAttribute(CURRENT_MESSAGE, "User UnBlocked succefully.");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}

