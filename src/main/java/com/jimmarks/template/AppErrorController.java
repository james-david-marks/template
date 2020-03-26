/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.template;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AppErrorController implements ErrorController{

    private final static String ERROR_PATH = "/error";

	@RequestMapping(ERROR_PATH)
	public String error(HttpServletRequest request, HttpServletResponse response) {
		App.applogger.info("error service invoked");
		HtmlFormatter html = new HtmlFormatter();
		return html.getLandingPage_build(getErrorHtml(request));		
	}

	private String getRequestEnumeration(HttpServletRequest request) {
		StringBuffer buf = new StringBuffer();
		Enumeration attributes = request.getAttributeNames();
		while(attributes.hasMoreElements())
		{
			String name = attributes.nextElement().toString();
			buf.append(String.format("%s", name));
			if(name.equals("javax.servlet.error.message"))
			{
				String attribute = request.getAttribute(name).toString();
				buf.append(String.format(": %s", attribute));
			}
			buf.append(String.format("<br/>"));
		}
		return buf.toString();
	}

	@Override
	public String getErrorPath() {		
		return ERROR_PATH;
	}
	
	private String getErrorHtml(HttpServletRequest request) {
		HtmlFormatter html = new HtmlFormatter();
		final String CONTEXT_PATH = "javax.servlet.forward.context_path";
		final String FORWARD_MAPPING = "javax.servlet.forward.forward_mapping";
		final String MESSAGE = "javax.servlet.error.message";
		final String REQUEST_URI = "javax.servlet.forward.request_uri";
		final String SERVLET_PATH = "javax.servlet.forward.servlet_path";
		final String STATUS = "javax.servlet.error.status_code";
		String contextPath = "unknown";
		String forwardMapping = "unknown";
		String message = "unknown";
		String requestUri = "unknown";
		String servletPath = "unknown";
		String status = "unknown";
		Enumeration attributes = request.getAttributeNames();
		while(attributes.hasMoreElements())
		{
			String name = attributes.nextElement().toString();
			switch(name)
			{
				default:
					break;
				case CONTEXT_PATH:
					contextPath = request.getAttribute(name).toString();
					break;
				case FORWARD_MAPPING:
					forwardMapping = request.getAttribute(name).toString();
					break;
				case MESSAGE:
					message = request.getAttribute(name).toString();
					break;
				case REQUEST_URI:
					requestUri = request.getAttribute(name).toString();
					break;
				case SERVLET_PATH:
					servletPath = request.getAttribute(name).toString();
					break;
				case STATUS:
					status = request.getAttribute(name).toString();
					break;
			}
		}
		return html.getErrorDivs(status, message, contextPath);
	}
	
}


