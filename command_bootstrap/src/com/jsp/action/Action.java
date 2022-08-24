package com.jsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//서블릿에 보고체계를 만들기 위해 throws
	//url를 리턴 -> String
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
