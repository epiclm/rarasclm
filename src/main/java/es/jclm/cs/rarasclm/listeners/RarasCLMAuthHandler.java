package es.jclm.cs.rarasclm.listeners;

import java.io.IOException;
import java.util.Date;

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
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import es.jclm.cs.rarasclm.entities.IBaseModel;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;

public class RarasCLMAuthHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	static Log log = LogFactory.getLog(RarasCLMAuthHandler.class.getName());
	
	@Autowired 
	UsuarioService userService;
	
	@Autowired
	public IBaseModel base;
	
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	            Authentication authentication) throws ServletException, IOException {
	    	
    		String esquema = request.getScheme();
    		String port =  request.getServerPort()==80 ? "" : Integer.toString(request.getServerPort());
    		String contexto =  request.getContextPath();
    		String serverName = request.getServerName();
    		String urlGenera = "";
    		if(port.equals(""))
    			urlGenera = String.format("%s://%s%s/%s",esquema,serverName,contexto,"regenerapass");
    		else
    			urlGenera = String.format("%s://%s:%s%s/%s",esquema,serverName,port,contexto,"regenerapass");
	    	
    		UserRarasClm userClm = (UserRarasClm)request.getSession().getAttribute("userCLM");
	    	
	        Date ultimoAcceso = userClm.getUltimoAcceso();
	        userClm.setUltimoAcceso(new Date());
	        userClm.setEnabled(true);
	        userClm.setNumIntentos(3);
	        try {
	        	if(!userClm.getGenerar()) //Si el usuario está en estado generar no se guarda
	        		userService.Actualizar(userClm);
			} catch (ServiceRarasCLMException ex) {
				log.error(ex.getMessage(),ex);
			}
	        userClm.setUltimoAcceso(ultimoAcceso);
	    	
	        SavedRequest savedRequest = requestCache.getRequest(request, response);

	        if (savedRequest == null && !userClm.getGenerar()) {
					super.onAuthenticationSuccess(request, response, authentication);
					return;
			}
	      
	        String targetUrlParameter = getTargetUrlParameter();
	        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
	            requestCache.removeRequest(request, response);
	            super.onAuthenticationSuccess(request, response, authentication);
	            return;
	        }

	        clearAuthenticationAttributes(request);
	        
	        String targetUrl = null;

	        // Use the DefaultSavedRequest URL
	        if(savedRequest!=null) {
	        	targetUrl = savedRequest.getRedirectUrl();
	        	logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
	        }

	        
        
	        request.getSession().removeAttribute("autentica");
	        if(!userClm.getGenerar()) {
	        	getRedirectStrategy().sendRedirect(request, response, targetUrl);
	        } else {
	        	getRedirectStrategy().sendRedirect(request, response, urlGenera);
	        }
	    }

	    public void setRequestCache(RequestCache requestCache) {
	        this.requestCache = requestCache;
	    }


}
