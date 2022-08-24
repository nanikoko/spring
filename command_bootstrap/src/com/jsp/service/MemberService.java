package com.jsp.service;

import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberService {
	public List<MemberVO> getMemberList() throws Exception;
	public List<MemberVO> getMemberList(Criteria cri) throws Exception;
	
	public Map<String, Object> getMemberListForPage(Criteria cri) throws Exception;

	public MemberVO getMember(String id) throws Exception;
	
	public void regist(MemberVO member) throws Exception;
	public void modify(MemberVO member) throws Exception;
	public void remove(String id) throws Exception;
	
	//회원상태변경
	void enabled(String id, int enabled) throws Exception; 
}
