package com.jsp.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberIdCheckAction implements Action {
	private MemberService memberService;
	public void setSearchMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultStr = "";
		
		String id = request.getParameter("id");
		
		try {
			MemberVO member = memberService.getMember(id);
			
			if(member != null) { //아이디가 있는 경우
				resultStr = "DUPLICATED";
			}
			
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultStr);
			out.close();
		} catch (Exception e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
