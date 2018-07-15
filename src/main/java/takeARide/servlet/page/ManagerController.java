package takeARide.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.Constants;
import takeARide.entity.Order;
import takeARide.model.CurrentAccount;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;

@WebServlet("/manager-controller")
public class ManagerController extends AbstractController {
	private static final long serialVersionUID = -1782066337808445826L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		List<Order> orders = getOrderService().listAllOrders(1, Constants.ORDERS_PER_PAGE);
		req.setAttribute("orders", orders);
		RoutingUtils.forwardToPage("Orders.jsp", req, resp);
	}
}