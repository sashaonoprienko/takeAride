package takeARide.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import takeARide.Constants;
import takeARide.service.Impl.ServiceManager;

@WebListener
public class TakeArideApplicationListener implements ServletContextListener {
	private static final Logger LOGGER =  LoggerFactory.getLogger(TakeArideApplicationListener.class);
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
			sce.getServletContext().setAttribute(Constants.CATEGORY_LIST, serviceManager.getProductService().listAllCategories());
			sce.getServletContext().setAttribute(Constants.PRODUCER_LIST, serviceManager.getProductService().listAllProducers());
		} catch (RuntimeException e) {
			LOGGER.error("Web application 'takeAride' init failed: "+e.getMessage(), e);
			throw e;
		}
		LOGGER.info("Web application 'takeAride' initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Web application 'takeAride' destroyed");
	}
}
