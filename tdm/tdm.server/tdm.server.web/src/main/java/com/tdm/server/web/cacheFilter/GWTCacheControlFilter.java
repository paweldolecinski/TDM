package com.tdm.server.web.cacheFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GWTCacheControlFilter implements Filter {

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain filterChain) throws IOException, ServletException {

	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpresponse = (HttpServletResponse) response;
	String requestURI = httpRequest.getRequestURI();
	if (requestURI.contains("checkAuth")) {
	    String attribute = httpRequest.getContentType();
	    System.out.println("Attr: " + attribute);
	    if (attribute == null || !attribute.contains("text/x-gwt-rpc")) {
		System.out.println("LLLLLLLLLOOOOOOOOOLLLLLLLLLLL");
		 httpresponse.sendRedirect("/");
		return;
	    }
	    System.out.println("Post");
	}
	System.out.println("DoFilter");
	filterChain.doFilter(request, response);
    }
}