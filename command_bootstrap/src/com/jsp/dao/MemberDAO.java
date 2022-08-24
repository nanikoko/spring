package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	//세션을 받아서 사용
	//예외처리를 throws(try-catch이 아니라)하는 이유 -> 우려가 되는 문제를 미리 대비하고, 문제가 생겼을 경우 컨트롤러에게 알려주기 위해(보고체계)
	//try-catch와 throws는 별개 (둘중에 하나만 해도 되는 건 아님)
	List<MemberVO> selectMemberList(SqlSession session) throws Exception;
	//오버로딩 -> 기능 확장
	List<MemberVO> selectMemberList(SqlSession session,Criteria cri) throws Exception;

	//totalCount
	//오버로딩 -> 리턴타입만 다르면 메서드명을 다르게 해야함
	int selectMemberListCount(SqlSession session) throws Exception;
	
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;
	public void updateMember(SqlSession session, MemberVO member) throws SQLException;
	void deleteMember(SqlSession session, String id) throws SQLException;
	
	//회원 활성화
	void enabledMember(SqlSession session, String id, int enabled) throws SQLException;
}

