package takeARide.servlet.page;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.Constants;
import takeARide.entity.Product;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController {

	private static final long serialVersionUID = 1084613642340508997L;
	private static final int SUBSTRING_INDEX = "products/".length();
    
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	resp.setCharacterEncoding("UTF-8");
    	String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Product> products = getProductService().listProductsByCategory(categoryUrl, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        if(categoryUrl.equals("/cheap-to-expensive")) {
    		Collections.sort(products);
    	}else if(categoryUrl.equals("/expensive-to-cheap")) {
    		Collections.sort(products, Collections.reverseOrder());
    	}
		req.setAttribute("products", products);
		int totalCount = getProductService().countProductsByCategory(categoryUrl);
		req.setAttribute("pageCount", getPageCount(totalCount, 16));
		req.setAttribute("selectedCategoryUrl", categoryUrl);
		RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
