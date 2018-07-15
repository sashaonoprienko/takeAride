package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/product/upByAdmin")
public class UpdateProductByAdmin extends AbstractController  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9049563685023820245L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getOrderService().updateProduct(req.getParameter("name"),req.getParameter("description"),req.getParameter("price"), req.getParameter("idProduct"));
		
		req.getSession().setAttribute(CURRENT_MESSAGE, "Product updated succefully.");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}
