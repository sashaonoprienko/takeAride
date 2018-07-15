package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/product/orderDone")
public class OrderDoneControler extends AbstractController  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9049563685023820245L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String message = req.getParameter("message");
		 
              if(message !=null) {
            	  getOrderService().doneDamagedOrder(Integer.parseInt(req.getParameter("id")),message);
              }
            	  
              
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	

}