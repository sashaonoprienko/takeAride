package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.entity.Order;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;




@WebServlet("/ajax/json/product/addByAdmin")
public class AddProductByAdmin extends AbstractController  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9049563685023820245L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getOrderService().addNewProduct(req.getParameter("name"), req.getParameter("producer"), req.getParameter("category"),
				req.getParameter("price"), req.getParameter("description"), req.getParameter("idProduct"),
				req.getParameter("file"));
		
		req.getSession().setAttribute(CURRENT_MESSAGE, "Product added succefully.");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}
