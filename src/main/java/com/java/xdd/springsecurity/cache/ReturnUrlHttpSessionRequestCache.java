package com.java.xdd.springsecurity.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class ReturnUrlHttpSessionRequestCache extends HttpSessionRequestCache {
	
	static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

	private PortResolver portResolver = new PortResolverImpl();
    private boolean createSessionAllowed = true;
    private RequestMatcher requestMatcher = AnyRequestMatcher.INSTANCE;
    
	private String targetUrlParameter;
	
	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}
	
	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}

    /**
     * Stores the current request, provided the configuration properties allow it.
     */
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
    	
        if (requestMatcher.matches(request)) {
        	ReturnUrlSavedRequest savedRequest = new ReturnUrlSavedRequest(request, portResolver, getTargetUrlParameter());

            if (createSessionAllowed || request.getSession(false) != null) {
                // Store the HTTP request itself. Used by AbstractAuthenticationProcessingFilter
                // for redirection after successful authentication (SEC-29)
                request.getSession().setAttribute(SAVED_REQUEST, savedRequest);
                logger.debug("ReturnUrlSavedRequest added to Session: " + savedRequest);
            }
        } else {
            logger.debug("Request not saved as configured RequestMatcher did not match");
        }
    }
    
    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public void setCreateSessionAllowed(boolean createSessionAllowed) {
        this.createSessionAllowed = createSessionAllowed;
    }

    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }
}