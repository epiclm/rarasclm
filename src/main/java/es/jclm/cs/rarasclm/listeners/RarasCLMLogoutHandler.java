package es.jclm.cs.rarasclm.listeners;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
 
public class RarasCLMLogoutHandler implements LogoutSuccessHandler{
 
	
	static Log log = LogFactory.getLog(RarasCLMLogoutHandler.class.getName());
	
	@Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        
		
		if (authentication != null && authentication.getDetails() != null) {
            try {
          
                httpServletRequest.getSession().invalidate();
                System.out.println("User Successfully Logout");
                //you can add more codes here when the user successfully logs out,
                //such as updating the database for last active.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        //redirect to login
        httpServletResponse.sendRedirect("autentica");
    }
}