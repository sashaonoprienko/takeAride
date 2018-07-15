package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.servlet.AbstractController;

@WebServlet("/ajax/json/hist")
public class OrderHistoryController extends AbstractController {
	private static final long serialVersionUID = -3440773010973766553L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  if(req.getSession().getAttribute("history") != null) {
	   req.getSession().removeAttribute("history");
	  }else {
		  req.getSession().setAttribute("history", 1);
	  }
	}

	
}

