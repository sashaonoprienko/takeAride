package takeARide.listener;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import takeARide.Constants;
@WebListener
@SuppressWarnings("unchecked")
public class AccountSesionStatistickListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("!!!!");
		List<String> actions = (List<String>) se.getSession().getAttribute(Constants.ACCOUNT_ACTIONS_HISTORY);
		if(actions != null) {
			logCurrentAccountHistory(se.getSession().getId(),actions);
		}
	}

	private void logCurrentAccountHistory(String sessionId, List<String> actions) {	
		System.out.println(sessionId + " ->\n\t" + String.join("\n\t", actions));
		System.out.println("!??!");
	}

}
