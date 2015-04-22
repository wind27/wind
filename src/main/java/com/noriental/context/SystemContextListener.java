package com.noriental.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.noriental.common.Constants;

/**
 *
 * @author Wang Beichen
 * @date 2014-1-16
 * @version 1.0
 */
public class SystemContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(SystemContextListener.class);

	private ServletContext sc = null;
	
	private static WebApplicationContext wac = null;

	public void contextInitialized(ServletContextEvent event){  
		sc  = event.getServletContext();
		wac = WebApplicationContextUtils.getWebApplicationContext(sc);  
		loadMetadata();
	}

	private void loadMetadata() {
		ContextWrapper cw = new ContextWrapper(wac);
		try {
			sc.setAttribute(Constants.METADATA, cw.getMetadata());
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Object getMetadata() {
		return wac.getServletContext().getAttribute(Constants.METADATA);
    }
	
	public void contextDestroyed(ServletContextEvent event) {
//		ProxoolFacade.shutdown(0);
        logger.debug("System context destroyed");
	}  

}
