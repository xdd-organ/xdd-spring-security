package com.java.xdd.springsecurity.source;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import com.java.xdd.springsecurity.domain.Role ;

/**
 * 此类在初始化时，应该取到所有资源及其对应角色的定义,//1 加载资源与权限的对应关系
 * 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 */
@Service("adminSecurityMetadataSource")
public class AdminSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	private boolean useAntPath = true;
	private boolean lowercaseComparisons = true;

	/**
	 * 返回所请求资源所需要的权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String requestUrl = filterInvocation.getRequestUrl();
		log.debug("request url:" + requestUrl);
		// //加载所有资源与权限的关系
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

		Set<String> keySet = resourceMap.keySet();
		HttpServletRequest request = filterInvocation.getRequest();
		Set<ConfigAttribute> configAttributeSet = new HashSet<>();

		// 判断该URL是否有特别权限控制
		boolean isURLControled = false;
		for (String pattern : keySet) {
			if (StringUtils.isEmpty(pattern)) {
				continue;
			}
			RequestMatcher matcher = null;
			if (useAntPath) {
				matcher = new AntPathRequestMatcher(pattern);
			} else {
				String method = request.getMethod();
				matcher = new RegexRequestMatcher(pattern, method);
			}

			boolean match = matcher.matches(request);
			if (match) {
				isURLControled = true;
				Collection<ConfigAttribute> atts = resourceMap.get(pattern);
				configAttributeSet.addAll(atts);
			}
		}

		// 如果URL没有特殊控制，则设置为任何角色都可以访问
		if (!isURLControled) {

		}
		log.debug("admin security:requestURI=" + requestUrl + ";roles=" + configAttributeSet);
		return configAttributeSet;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 取得所有已配置的角色
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Collection<ConfigAttribute> atts = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		/*if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				System.out.println(role);
			}
		}*/

		for (int i = 0; i < 10; i ++) {
			atts.add(new SecurityConfig("/demo/test" + i));
		}
		atts.add(new SecurityConfig("/**"));

		return atts;
	}

	/**
	 * @return the useAntPath
	 */
	public boolean isUseAntPath() {
		return useAntPath;
	}

	/**
	 * @param useAntPath
	 *            the useAntPath to set
	 */
	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	/**
	 * @return the lowercaseComparisons
	 */
	public boolean isLowercaseComparisons() {
		return lowercaseComparisons;
	}

	/**
	 * @param lowercaseComparisons
	 *            the lowercaseComparisons to set
	 */
	public void setLowercaseComparisons(boolean lowercaseComparisons) {
		this.lowercaseComparisons = lowercaseComparisons;
	}
}
