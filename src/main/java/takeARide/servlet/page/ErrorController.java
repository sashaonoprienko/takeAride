package takeARide.servlet.page;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;


@WebServlet("/error")
public class ErrorController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setAttribute("statusCode", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		RoutingUtils.forwardToPage("error.jsp", req, resp);
	}
}