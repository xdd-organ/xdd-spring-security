package com.java.xdd.springsecurity.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.http.FilterInvocationSecurityMetadataSourceParser;
import org.springframework.security.web.FilterInvocation;
import org.w3c.dom.Element;

public class MyFilterInvocationSecurityMetadataSource extends FilterInvocationSecurityMetadataSourceParser{
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public BeanDefinition parse(Element arg0, ParserContext arg1) {
		return super.parse(arg0, arg1);
	}
	
	// tomcat启动时实例化一次
	public MyFilterInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<>();
		Collection<ConfigAttribute> atts = new ArrayList<>();
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
		atts.add(ca);
		resourceMap.put("/index.jsp", atts);
		Collection<ConfigAttribute> attsno = new ArrayList<>();
		ConfigAttribute cano = new SecurityConfig("ROLE_NO");
		attsno.add(cano);
		resourceMap.put("http://blog.csdn.net/u012367513/article/details/other.jsp",attsno);
	}

	// 参数是要访问的url，返回这个url对于的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		System.out.println("zhi xing fang fa");
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}	
}
