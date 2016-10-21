package es.jclm.cs.rarasclm.listeners;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import es.jclm.cs.rarasclm.entities.AutenticaModel;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;

public class RarasCLMLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
		@Autowired
		UsuarioService usuarioService;
		
		private static final Logger log = LoggerFactory.getLogger(RarasCLMLoginFailureHandler.class);
		
		private String defaultFailureUrl="/autentica";
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		AutenticaModel autenticaModel = new AutenticaModel();
		
		autenticaModel.setError(true);
		
		if (defaultFailureUrl == null) {
			logger.debug("No failure URL set, sending 401 Unauthorized error");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
		} else {
			saveException(request, exception);
			if(exception instanceof BadCredentialsException) {
				UserRarasClm userClm = (UserRarasClm)request.getSession().getAttribute("userCLM");
				if(userClm!=null) {
					userClm.setNumIntentos(userClm.getNumIntentos()-1);
					try {
						usuarioService.Actualizar(userClm);
					} catch (ServiceRarasCLMException ex) {
						log.error(ex.getMessage(),ex);
					}
					autenticaModel.setMensaje(String.format("Credenciales erróneas. Al usuario %s le quedan %d intentos",userClm.getUsername(),userClm.getNumIntentos()));
				}	else {
					autenticaModel.setMensaje("Credenciales erróneas. El usuario no existe");
				}
			}	
			if(exception instanceof DisabledException) {
				UserRarasClm userClm = (UserRarasClm)request.getSession().getAttribute("userCLM");
				autenticaModel.setMensaje(String.format("Usuario %s desabilitado. Contacte con un administrador para habilitar de nuevo la cuenta o restablecer la contraseña.",userClm.getUsername()));
			}
			if(exception instanceof InternalAuthenticationServiceException) {
				autenticaModel.setMensaje(String.format("Error interno de autenticación. Contacte con un administrador para habilitar de nuevo la cuenta o restablecer la contraseña."));
			}
			request.getSession().setAttribute("autentica", autenticaModel);			
            logger.debug("Redirecting to " + defaultFailureUrl);
            redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
		}
	}
}
