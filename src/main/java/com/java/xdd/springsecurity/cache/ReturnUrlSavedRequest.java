package com.java.xdd.springsecurity.cache;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class ReturnUrlSavedRequest extends DefaultSavedRequest {
	
	private String targetUrlParameter;
	
	public ReturnUrlSavedRequest(HttpServletRequest request, PortResolver portResolver, String targetUrlParameter) {
		super(request, portResolver);
		this.targetUrlParameter = targetUrlParameter;
	}
	
	@Override
	public String getRedirectUrl() {
		if (StringUtils.isNotBlank(targetUrlParameter)) {
			String[] targetUrls = super.getParameterValues(targetUrlParameter);
			if (targetUrls != null && targetUrls.length > 0) {
				String targetUrl = targetUrls[0];
				if (StringUtils.isNotBlank(targetUrl)) {
					return targetUrl;
				}
			}
		}
		return super.getRedirectUrl();
	}

}
