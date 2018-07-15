package takeARide.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import takeARide.Constants;
import takeARide.utils.RoutingUtils;
import takeARide.utils.SessionUtils;
import takeARide.utils.UrlUtils;
import takeARide.utils.WebUtils;




@WebFilter(filterName = "CheckAuthentificationFilter")
public class CheckAuthentificationFilter extends AbstractFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (SessionUtils.isCurrentAccountCreated(req)) {
			chain.doFilter(req, resp);
		} else {
			String requestUrl = WebUtils.getCurrentRequestUrl(req);
			if (UrlUtils.isAjaxUrl(requestUrl)) {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				RoutingUtils.sendHTMLFragment("401", req, resp);
			} else {
				req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, requestUrl);
				RoutingUtils.redirect("/sign-in", req, resp);
			}
		}
	}
}
