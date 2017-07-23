package com.java.xdd.springsecurity.filter;

import java.util.Collection;

import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * <!-- 用户是否拥有所请求资源的权限 -->
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
	
//	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
////		System.err.println(" ---------------  MyAccessDecisionManager --------------- ");
//		if(configAttributes == null) {
//			return;
//		}
//		//所请求的资源拥有的权限(一个资源对多个权限)
//		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
//		while(iterator.hasNext()) {
//			ConfigAttribute configAttribute = iterator.next();
//			//访问所请求资源所需要的权限
//			String needPermission = configAttribute.getAttribute();
//			//System.out.println("needPermission is " + needPermission);
//			//用户所拥有的权限authentication
//			for(GrantedAuthority ga : authentication.getAuthorities()) {
//				if(needPermission.equals(ga.getAuthority())) {
//					return;
//				}
//			}
//		}
//		//没有权限
//		throw new AccessDeniedException(" 没有权限访问！ ");
//	}

	// 检查用户是否够权限访问资源
		// 参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
		// 参数object是url
		// 参数configAttributes所需的权限
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}

		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) {

					return;
				}
			}
		}
		// 注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面
		throw new AccessDeniedException("no right");
	}
	
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}