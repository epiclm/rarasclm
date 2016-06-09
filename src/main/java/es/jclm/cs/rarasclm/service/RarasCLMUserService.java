package es.jclm.cs.rarasclm.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.dao.UserRarasCLMDao;
import es.jclm.cs.rarasclm.entities.RolesRarasCLM;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;


@Service
public class RarasCLMUserService implements UserDetailsService {
	
	@Autowired
	private UserRarasCLMDao userDao;
	
	@Autowired
	private HttpServletRequest request;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRarasCLM userCLM = null;
		List<GrantedAuthority> authorities = null;
		if(username!=null || username=="") {
			userCLM = userDao.findByUserName(username);
			authorities = buildUserAuthority(userCLM.getRoleses());
			request.getSession().setAttribute("userCLM",userCLM);
			return  buildUserForAuthentication(userCLM,authorities); 
		} else {
			throw new UsernameNotFoundException("El nombre de usuario no puede estar vacío");
		}	
	}


	private User buildUserForAuthentication(UserRarasCLM user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), 
			user.getPassword(), user.isEnabled(), 
                        true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<RolesRarasCLM> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (RolesRarasCLM userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getDeno()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}


}
