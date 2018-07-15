package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.form.ProductForm;
import takeARide.model.ShoppingCart;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;

@WebServlet("/ajax/json/toRus")
public class LanguageSwitchertoRus extends AbstractController {
	private static final long serialVersionUID = -3440773010973766553L;
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	   req.getSession().setAttribute("language", "Russ");
	   RoutingUtils.forwardToPage("products.jsp", req, resp);
	 
	}
    
    
}
