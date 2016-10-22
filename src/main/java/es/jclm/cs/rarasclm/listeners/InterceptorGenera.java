package es.jclm.cs.rarasclm.listeners;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.jclm.cs.rarasclm.entities.UserRarasClm;

public class InterceptorGenera extends HandlerInterceptorAdapter {

	static Log log = LogFactory.getLog(InterceptorGenera.class.getName());

	
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
		    throws Exception {
		
	
			UserRarasClm userClm = (UserRarasClm)request.getSession().getAttribute("userCLM");

			if(userClm!=null && userClm.getGenerar()) {
				String esquema = request.getScheme();
				String port =  request.getServerPort()==80 ? "" : Integer.toString(request.getServerPort());
				String contexto =  request.getContextPath();
				String serverName = request.getServerName();
				String urlGenera = "";
				if(port.equals(""))
					urlGenera = String.format("%s://%s%s/%s",esquema,serverName,contexto,"regenerapass");
				else
					urlGenera = String.format("%s://%s:%s%s/%s",esquema,serverName,port,contexto,"regenerapass");
	    	
				response.sendRedirect(urlGenera);
			}
			
			return true;
		}

		//after the handler is executed
		public void postHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {

		
		}
}
