package com.jsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dao.BoardDAO;
import com.jsp.dao.BoardDAOImpl;
import com.jsp.dataSource.OracleMybatisSqlSessionFactory;
import com.jsp.vo.Board;

public class BoardServiceImpl implements BoardService{
	private SqlSessionFactory sqlSessionFactory = new OracleMybatisSqlSessionFactory();

	private BoardDAO boardDAO = new BoardDAOImpl();

	@Override
	public List<Board> getBoardList() throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<Board> boardList = null;
		
		try {
			boardList = boardDAO.selectBoardList(session);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return boardList;
	}
	
	@Override
	public List<Board> getBoardList(Criteria cri) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<Board> boardList = null;
		
		try {
			boardList = boardDAO.selectBoardList(session, cri);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return boardList;
	}

	@Override
	public Map<String, Object> getBoardListForPage(Criteria cri) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		Map<String, Object> dataMap = null;
		System.out.println("서비스");
		
		try {
			List<Board> boardList = boardDAO.selectBoardList(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(boardDAO.selectBoardListCount(session));
			
			dataMap = new HashMap<String, Object>();
			dataMap.put("boardList", boardList);
			dataMap.put("pageMaker", pageMaker);
			
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return dataMap;
	}
}
