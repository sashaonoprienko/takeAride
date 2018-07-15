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


@WebServlet("/ajax/json/setChecked")
public class setCheckedOrder extends AbstractController {
	private static final long serialVersionUID = -1782066337808445826L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getOrderService().setCheckedForOrder(Integer.parseInt(req.getParameter("id")));
	}
}
