package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.InternalViewResolver;
import com.jsp.dto.MemberVO;

public class LoginUserChecekFilter implements Filter {
	private List<String> exURLs = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		//제외할 url 가져옴 -> login,logout
		String excludeURLNames = fConfig.getInitParameter("exclude");
		
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while(st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//filter에서 servlet으로 보내기 위한 형변환
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		//제외할 url확인
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}
		
		//login check
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		//login확인
		if(loginUser==null) { //비로그인상태
			String contextPath = httpReq.getContextPath();
			String retUrl = httpReq.getRequestURI().replace(contextPath, ""); //return할 url
//			String mCode = httpReq.getParameter("mCode");
//			if(mCode!=null) {
//				retUrl += "?mCode="+mCode;
//			}
			String queryString = httpReq.getQueryString();
			if(queryString!=null) {
				retUrl+="?"+queryString;
			}
			
			httpReq.setAttribute("viewName", "redirect:/common/loginForm.do?error=-1&retUrl=" + retUrl);
			InternalViewResolver.view(httpReq, httpResp);
		}else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {}

	private boolean excludeCheck(String url) { //로그인이 필요없는 경우 filter통과 -> true
		boolean result = false;
		result = result || url.length() <= 1; //contextPath까지만 있는 경우
		for(String exURL : exURLs) {
			result = result || url.contains(exURL);
		}
		return result;
	}
	
}