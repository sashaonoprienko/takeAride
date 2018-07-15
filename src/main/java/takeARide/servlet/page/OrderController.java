package takeARide.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.entity.Order;
import takeARide.model.ShoppingCart;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;

@WebServlet("/order")
public class OrderController extends AbstractController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7704355805943997642L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		long idOrder = getOrderService().makeOrder(shoppingCart, SessionUtils.getCurrentAccount(req),req);
		SessionUtils.clearCurrentShoppingCart(req, resp);
		req.getSession().setAttribute(CURRENT_MESSAGE, "Order created successfully. Please wait for our reply.");
		RoutingUtils.redirect("/order?id=" + idOrder, req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = (String) req.getSession().getAttribute(CURRENT_MESSAGE);
		req.getSession().removeAttribute(CURRENT_MESSAGE);
		req.setAttribute(CURRENT_MESSAGE, message);
		Order order = getOrderService().findOrderById(Long.parseLong(req.getParameter("id")), SessionUtils.getCurrentAccount(req));
		req.setAttribute("order", order);
		RoutingUtils.forwardToPage("order.jsp", req, resp);
	}
}