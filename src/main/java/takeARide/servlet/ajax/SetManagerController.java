package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/product/setManager")
public class SetManagerController extends AbstractController  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1911441091705710233L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getOrderService().setManager(req.getParameter("useremail"));
		
		req.getSession().setAttribute(CURRENT_MESSAGE, "Manager set succefully.");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}
