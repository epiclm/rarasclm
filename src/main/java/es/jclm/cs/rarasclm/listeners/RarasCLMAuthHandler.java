package es.jclm.cs.rarasclm.listeners;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class RarasCLMAuthSuccess extends SimpleUrlAuthenticationSuccessHandler {
	
	static Log log = LogFactory.getLog(RarasCLMAuthSuccess.class.getName());
	
	@Autowired
	HttpServletRequest req;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		//Aquí guarda spring web la url desde la que se accede pero no tiene
		//autorización y se llama a este controlador
		DefaultSavedRequest defRequest = (DefaultSavedRequest)req.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		this.setDefaultTargetUrl(defRequest.getRedirectUrl());
		
		super.onAuthenticationSuccess(request,response,authentication);
		
		log.info("Login OK");
	}


}
