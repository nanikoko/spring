package com.jsp.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dataSource.DataSource;
import com.jsp.vo.Board;

@WebServlet("/board/regist")
public class BoardRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource = DataSource.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/views/board/regist.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = dataSource.getBoardNo();
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String url = request.getContextPath()+"/board/list";
		
		Board board = new Board();
		board.setBno(bno);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setRegDate(new Date());
		board.setViewCnt(0);
		
		Map<String, Board> boardMap = dataSource.getBoardList();
		boardMap.put(bno+"", board);
		dataSource.getBoardList().put(bno+"", board);
		
		response.sendRedirect(url);
	}

}
