package com.java.xdd.springsecurity.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.java.xdd.springsecurity.domain.User;

/**
 * 
 * @author luoyh
 * 
 */
public class LoginAsAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAsAuthenticationProvider.class);

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("authenticate");
		Authentication prevAuth = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && prevAuth != null) {
			String prevUsername = prevAuth.getName();
			logger.info("previous user:" + prevUsername);
			String currUsername = authentication.getName();
			logger.info("login as user:" + currUsername);
			//if (systemConfig.getLoginAsUsername().contains(prevUsername)) {// 当前用户为系统管理员，可以login as any user

				UserDetails currUser = this.getUserDetailsService().loadUserByUsername(currUsername);
				Object principalToReturn = currUser;
				if (isForcePrincipalAsString()) {
					principalToReturn = currUser.getUsername();
				}
				if(currUser instanceof User){
					logger.info(prevUsername+"模拟"+currUsername+"进行登录。。。。。。。。。");
					return createSuccessAuthentication(principalToReturn, authentication, currUser);
				}
			//}
		}
		return super.authenticate(authentication);
	}

}
