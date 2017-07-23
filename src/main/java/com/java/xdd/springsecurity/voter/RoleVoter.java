package com.java.xdd.springsecurity.voter;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class RoleVoter extends org.springframework.security.access.vote.RoleVoter {
	
	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if(attributes.size()==1 ){
        	for (ConfigAttribute attribute : attributes){
        		if("ANY".equalsIgnoreCase(attribute.getAttribute())){
        			return ACCESS_GRANTED;
        		}
        	}
        }

        return result;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		System.out.println(attribute);
		return true;
	}
}
