package takeARide.servlet;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import takeARide.form.ProductForm;
import takeARide.form.SearchForm;
import takeARide.service.OrderService;
import takeARide.service.ProductService;
import takeARide.service.SocialService;
import takeARide.service.Impl.ServiceManager;



public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = -2031074947573473708L;
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private OrderService orderService;
	private ProductService productService;
	private SocialService socialService;
	@Override
	public final void init() throws ServletException {
		orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
		productService = ServiceManager.getInstance(getServletContext()).getProductService();
		socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
	}
	
	public final SocialService getSocialService() {
		return socialService;
	}

	public final OrderService getOrderService() {
		return orderService;
	}
	public final ProductService getProductService() {
		return productService;
	}
	public final int getPageCount(int totalCount, int itemsPerPage) {
		int res = totalCount / itemsPerPage;
		if(res * itemsPerPage != totalCount) {
			res++;
		}
		return res;
	}
	public final int getPage(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			return 1;
		}
	}
	
	public final SearchForm createSearchForm(HttpServletRequest request) {
		return new SearchForm(
				request.getParameter("query"), 
			request.getParameterValues("category"), 
				request.getParameterValues("producer"));
	}
	public final ProductForm createProductForm(HttpServletRequest request) {
		int driver;
		if(request.getParameter("driver") == null) {
			 driver = 0;
		}else {
			driver = 1;
		}
		return new ProductForm(
				Integer.parseInt(request.getParameter("idProduct")),
				Integer.parseInt(request.getParameter("count")),request.getParameter("passport"),request.getParameter("name"),driver);
		      
	}
}