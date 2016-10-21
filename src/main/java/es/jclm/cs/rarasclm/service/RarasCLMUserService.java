package es.jclm.cs.rarasclm.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.UserRarasCLMDao;
import es.jclm.cs.rarasclm.entities.RolRarasClm;
import es.jclm.cs.rarasclm.entities.UserRarasClm;


public class RarasCLMUserService implements UserDetailsService {
	
	@Autowired
	private UserRarasCLMDao userDao;
	
	@Autowired
	private HttpServletRequest request;
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRarasClm userCLM = null;
		List<GrantedAuthority> authorities = null;
		if(username!=null || username.trim().equals("")) {
			userCLM = userDao.findByUserName(username);
			if(userCLM==null) {
				throw new UsernameNotFoundException(String.format("El usuario con nombre %s no existe",username));
			}
			if(userCLM.getGenerar()) {
				String hash = BCrypt.hashpw(userCLM.getPassword(), BCrypt.gensalt());
				userCLM.setPassword(hash);
			}
			authorities = buildUserAuthority(userCLM.getRolRarasClms());
			request.getSession().setAttribute("userCLM",userCLM);
			return  buildUserForAuthentication(userCLM,authorities); 
		} else {
			throw new UsernameNotFoundException("El nombre de usuario no puede estar vacío");
		}	
	}
	
	public boolean isEnabledAndExists(String user) {
		return userDao.isUserEnabled(user);
	}

	private User buildUserForAuthentication(UserRarasClm user, List<GrantedAuthority> authorities) {
		if (user.getNumIntentos() <= 1) {
			user.setEnabled(false);
			user.setNumIntentos(0);
			try {
				userDao.actualizar(user);
			} catch (Exception ex) {
				log.error(ex.getMessage(),ex);
			}
		} else {
			
		}
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true,authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<RolRarasClm> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (RolRarasClm userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getDeno()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
	



}
