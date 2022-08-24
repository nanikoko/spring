package com.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param")
public class GetParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		
		String color = request.getParameter("color");
		//System.out.println(color);
		PrintWriter out = response.getWriter();
		
		out.println("<style>");
		out.println("body{background:");
		out.println(color);
		out.println("}");
		out.println("</style>");
	}


}
