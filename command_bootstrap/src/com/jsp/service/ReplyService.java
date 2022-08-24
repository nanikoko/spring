package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.ReplyVO;

public interface ReplyService {
	//리스트보기
	Map<String, Object> getReplyList(int bno, Criteria cri) throws SQLException;
	
	//리스트개수
	int getReplyListCount(int bno) throws SQLException;
	
	void registReply(ReplyVO reply) throws SQLException;
	void modifyReply(ReplyVO reply) throws SQLException;
	void removeReply(int rno) throws SQLException;
	
}
