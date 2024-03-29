package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;
import com.jsp.service.LoginSearchMemberService;

public class LoginAction implements Action {
	private LoginSearchMemberService loginSearchMemberService;
	public void setLoginSearchMemberService(LoginSearchMemberService loginSearchMemberServcie) {
		this.loginSearchMemberService = loginSearchMemberServcie;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="redirect:/index.do";
		
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String retUrl = request.getParameter("retUrl");
		
		if(retUrl!=null) url = "redirect:"+retUrl;
		
		try {
			loginSearchMemberService.login(id, pwd);
			
			//로그인상태유지
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginSearchMemberService.getMember(id));
			session.setMaxInactiveInterval(6*60); //톰켓기본설정 30분(로그인유지시간)
		
		} catch (NotFoundIdException | InvalidPasswordException e) {
			request.setAttribute("message", e.getMessage());
			url = "/common/login_fail";
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}

}
