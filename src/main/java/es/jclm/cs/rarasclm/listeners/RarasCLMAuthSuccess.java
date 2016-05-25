package es.jclm.cs.rarasclm.listeners;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RarasCLMAuthSuccess implements AuthenticationSuccessHandler {
	
	static Log log = LogFactory.getLog(RarasCLMAuthSuccess.class.getName());

	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication auth)
			throws IOException, ServletException {
		
		log.info("Login OK");

	}

}
