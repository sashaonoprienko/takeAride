package takeARide.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.Constants;
import takeARide.entity.Order;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;

@WebServlet("/ajax/html/more/my-orders")
public class MyOrdersMoreController extends AbstractController {
	private static final long serialVersionUID = -2651974520717714088L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Order> orders = getOrderService().listMyOrders(SessionUtils.getCurrentAccount(req), getPage(req), Constants.ORDERS_PER_PAGE);
		req.setAttribute("orders", orders);
		RoutingUtils.forwardToFragment("my-orders-tbody.jsp", req, resp);
	}
}