package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/product/rejectByManager")
public class RejectProductController extends AbstractController  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9049563685023820245L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getOrderService().rejectOrder(Integer.parseInt(req.getParameter("idorder")),req.getParameter("message"));
		
		req.getSession().setAttribute(CURRENT_MESSAGE, "Order rejected sucessfully.");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}

