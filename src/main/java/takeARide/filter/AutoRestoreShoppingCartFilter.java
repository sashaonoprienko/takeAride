package takeARide.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.form.ProductForm;
import takeARide.model.ShoppingCart;
import takeARide.model.ShoppingCartItem;
import takeARide.service.OrderService;
import takeARide.service.Impl.ServiceManager;
import takeARide.utils.SessionUtils;

@WebFilter(filterName="AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter extends AbstractFilter {
	private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";

	private OrderService orderService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
	}
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if(req.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null){
			if(!SessionUtils.isCurrentShoppingCartCreated(req)) {
				Cookie cookie = SessionUtils.findShoppingCartCookie(req);
				if(cookie != null) {
					ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
					if(shoppingCart != null) {
						SessionUtils.setCurrentShoppingCart(req, shoppingCart);
					}
				}
			}
			req.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
		}
		chain.doFilter(req, resp);
	}
}