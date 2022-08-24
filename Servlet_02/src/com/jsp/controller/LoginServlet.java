package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 화면 전송
		String url="/WEB-INF/views/common/login.jsp";
		request.getRequestDispatcher(url).forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 입력(id/pwd)
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		String message = "";
		
		//로그인 처리
		//(A&&B - A가 false이면 B를 실행안함 -> null판단비교를 먼저해야함)
		//(A||B도 마찬가지)
		if(id!=null && id.equals("mimi")) {
			if(pwd!=null && pwd.equals("mimi")) {
				message="로그인에 성공하였습니다.";
			}else {
				message="비밀번호가 일치하지 않습니다.";
			}
		}else {
			message="아이디가 일치하지 않습니다.";
		}
		
		//출력
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
	}

}
