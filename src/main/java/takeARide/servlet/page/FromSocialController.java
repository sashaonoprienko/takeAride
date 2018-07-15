package takeARide.servlet.page;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.Constants;
import takeARide.exceptions.BlockedUserExeption;
import takeARide.model.CurrentAccount;
import takeARide.model.SocialAccount;
import takeARide.servlet.AbstractController;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;

@WebServlet("/from-social")
public class FromSocialController extends AbstractController {
	private static final long serialVersionUID = -8146770694377066438L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String code = req.getParameter("code");
		if (code != null) {
			SocialAccount socialAccount = getSocialService().getSocialAccount(code);
			CurrentAccount currentAccount = getOrderService().authentificate(socialAccount);
			SessionUtils.setCurrentAccount(req, currentAccount);
			if(getOrderService().isBlocked(SessionUtils.getCurrentAccount(req).getEmail())) {
		      
				SessionUtils.delCurrentAccount(req);
				throw new BlockedUserExeption("You are Blocked!");
				
			}
			if(getOrderService().isManager(SessionUtils.getCurrentAccount(req).getEmail())){
				req.getSession().setAttribute("manager", 1);
				System.out.println("manager");
			}
			
			
			redirectToSuccessPage(req, resp);
			
		} else {
			LOGGER.warn("Parameter code not found");
			if(req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN) != null){
				req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
			}
			RoutingUtils.redirect("/sign-in", req, resp);
		}
	}
	
	protected void redirectToSuccessPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
		if (targetUrl != null) {
			req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
			RoutingUtils.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp);
		} else {
			RoutingUtils.redirect("/my-orders", req, resp);
		}
	}
}