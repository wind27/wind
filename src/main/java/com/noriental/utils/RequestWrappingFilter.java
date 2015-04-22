package com.noriental.utils;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class RequestWrappingFilter implements Filter{

	public void init(FilterConfig config) throws ServletException{
	}

	public void destroy(){
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
		chain.doFilter(new MyHttpRequestWrapper(req), response);
	}
}
