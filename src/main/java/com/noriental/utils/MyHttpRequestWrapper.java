package com.noriental.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;


public class MyHttpRequestWrapper extends HttpServletRequestWrapper{

	public MyHttpRequestWrapper(HttpServletRequest request) {  
	  super(request);  
	}  
	
	@Override
	public String getParameter(String name){
		String value = super.getParameter(name);
		value = HtmlUtils.htmlEscape(value);
		value = StringEscapeUtils.escapeSql(value);
		return value;
	}

	@Override
	public String[] getParameterValues(String name){
		String[] oldParams = super.getParameterValues(name);
		String[] values = null;
		if(oldParams==null || oldParams.length<=0) {
			return null;
		} else {
			values = new String[oldParams.length];
			for(int i=0; i<oldParams.length; i++) {
				values[i] = HtmlUtils.htmlEscape(oldParams[i]);
				values[i] = StringEscapeUtils.escapeSql(values[i]);
			}
		}
		return values;
	}
}