package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
//import com.jsp.command.Criteria;
import com.jsp.vo.Board;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public List<Board> selectBoardList(SqlSession session) throws Exception{
		List<Board> boardList = session.selectList("Board.selectBoardList");
		return boardList;
	}

	@Override
	public List<Board> selectBoardList(SqlSession session, Criteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Board> boardList = session.selectList("Board.selectBoardList",null,rowBounds);
		return boardList;
	}

	@Override
	public int selectBoardListCount(SqlSession session) throws Exception {
		int totalCount = session.selectOne("Board.selectBoardListCount");
		return totalCount;
	}

}
