package com.jsp.service;

import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.vo.Board;

public interface BoardService {
	public List<Board> getBoardList() throws Exception;
	
	public List<Board> getBoardList(Criteria cri) throws Exception;
	
	public Map<String, Object> getBoardListForPage(Criteria cri) throws Exception;
}
