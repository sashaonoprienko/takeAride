package takeARide.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.form.ProductForm;
import takeARide.model.CurrentAccount;
import takeARide.model.ShoppingCart;
import takeARide.model.SocialAccount;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;
@WebServlet("/ajax/json/product/add")
public class AddProductController extends AbstractProductController {
	private static final long serialVersionUID = -3440773010973766553L;

	@Override
	protected void processProductForm(ProductForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {

	    form.setNeedDriver(Integer.parseInt(req.getParameter("driver")));
		getOrderService().addProductToShoppingCart(form, shoppingCart);
		String cookieValue = getOrderService().serializeShoppingCart(shoppingCart);
		SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
		
	}
}
