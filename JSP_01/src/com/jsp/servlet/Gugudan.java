package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력
		int dan = 2;
		String result = "";
	
		//처리
		for(int i=1; i<10; i++){
			result += dan + "*" + i + "=" + (dan*i) + "<br>"; 
		}
		
		request.setAttribute("result", result); //map(키,값) - 파라미터도 같음
		//Dispatcher - request를 관장하여 forward를 관리
		//forward - 요청상태를 유지하면서 넘어가도록 함
		request.getRequestDispatcher("/gugudan.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
