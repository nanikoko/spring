package com.jsp.action.member;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.command.MemberModifyCommand;
import com.jsp.command.MemberRegistCommand;
import com.jsp.controller.FileUploadResolver;
import com.jsp.controller.GetUploadPath;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.MultiParameterAdapter;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberModifyAction implements Action {
	private MemberService memberService;
	public void setSearchMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; //500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; //1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; //2MB

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/modify_success";
		
		MultipartHttpServletRequestParser multi = 
				new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);

		MemberVO modifiedMember = MultiParameterAdapter.execute(multi, MemberVO.class);
		
		String id = multi.getParameter("id");
		MemberVO memberTarget = memberService.getMember(id); 
		
		String uploadPicture = multi.getParameter("uploadPicture");
		try {
			//기존 사진 변경 유무 확인
			if(uploadPicture != null && !uploadPicture.isEmpty()) {				
				String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
				
				FileItem[] items = multi.getFileItems("picture");
				List<File> uploadFiles = FileUploadResolver.fileUpload(items, uploadPath);
				String uploadFileName = uploadFiles.get(0).getName();
				
				//기존 사진이미지 삭제
				String oldPicture = memberTarget.getPicture();
				if(oldPicture != null) {				
					File oldFile = new File(uploadPath + File.separator + oldPicture);
					if(oldFile.exists()) {
						oldFile.delete();
					}
				}
				modifiedMember.setPicture(uploadFileName);
			}else {
				modifiedMember.setPicture(memberTarget.getPicture());
			}
			//DB처리
			memberService.modify(modifiedMember);
			request.setAttribute("member", modifiedMember);
		}catch(Exception e) {
			e.printStackTrace();
			url = "/member/modify_fail";
		}
		
		//로그인 사용자 확인
		request.setAttribute("parentReload",false);

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser!=null && modifiedMember.getId().equals(loginUser.getId())) { //로그인된 경우
			request.setAttribute("parentReload",true);
			session.setAttribute("loginUser", memberService.getMember(modifiedMember.getId()));
		}
		return url;
	}

}
