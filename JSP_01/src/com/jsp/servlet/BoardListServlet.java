package com.jsp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.command.Criteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.vo.Board;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private DataSource dataSource = DataSource.getInstance();
	private BoardService boardService = new BoardServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/board/list.jsp";
		
		String pageParam = request.getParameter("page");
		String perPageNumParam = request.getParameter("perPageNum");
		
		Criteria cri = new Criteria();
		
		boolean criFlag = true; //누적변수
		criFlag = criFlag && pageParam!=null && !pageParam.isEmpty()
						&& perPageNumParam!=null && !perPageNumParam.isEmpty();
		if(criFlag) {
			try {
				cri.setPage(Integer.parseInt(pageParam));
				cri.setPerPageNum(Integer.parseInt(perPageNumParam));
			}catch(Exception e) {
				response.sendError(response.SC_BAD_REQUEST);
				return;
			}
		}
		
		//Map<String, Board> memberMap = dataSource.getBoardList();
		//List<Board> boardList = new ArrayList<Board>(memberMap.values());
		
		try {
//			List<Board> boardList = boardService.getBoardList(cri);
//			request.setAttribute("boardList", boardList);
			
			System.out.println("서블릿");
			Map<String, Object> dataMap = boardService.getBoardListForPage(cri);
			request.setAttribute("dataMap", dataMap);
		}catch(Exception e){
			url = "/WEB-INF/views/error/500.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
