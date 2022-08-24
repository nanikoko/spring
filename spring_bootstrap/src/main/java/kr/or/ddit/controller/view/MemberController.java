package kr.or.ddit.controller.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.MemberRegistCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.MakeFileName;
import com.jsp.dto.MemberVO;
import com.jsp.service.LoginSearchMemberService;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.controller.advisor.ExceptionLoggerHelper;
import kr.or.ddit.controller.rest.MemberRestController;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private LoginSearchMemberService memberService; //생성자
	@Autowired
	private ExceptionLoggerHelper exceptionLogger;

	@RequestMapping("/main")
	public void main() {}

	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, HttpServletRequest request, ModelAndView mnv) throws SQLException{
		String url = "member/list";
		Map<String, Object> dataMap = null;
		try {
			dataMap = memberService.getMemberListForPage(cri);
		}catch(SQLException e) {
			exceptionLogger.write(request, e, "MemberService");
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
		}
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping(value="/excel")
	public void excel(HttpServletResponse response, SearchCriteria cri, HttpSession session) throws Exception {
	// 데이터를 넣을 객체
	//System.out.println(saList);
	Map<String, Object> memberList = memberService.getMemberListForPage(cri);

	List<MemberVO> memberList2 = (List<MemberVO>) memberList.get("memberList");

	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	String fileName =  loginUser.getName()+"_";// 파일 이름
	String title = "회원 목록"; // 제목 셀 이름
	Date day = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy_MM");
	String today = format.format(day); // 오늘 날짜 생성

	ClassPathResource classPathResource = new ClassPathResource("kr/or/ddit/templates/excel/sample.xls");

	try (InputStream is = new BufferedInputStream(classPathResource.getInputStream())){
		response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName, "UTF-8") + today +".xlsx");
		OutputStream os = response.getOutputStream();

		Context context = new Context();
		context.putVar("memberList", memberList2);
		context.putVar("title", title);

		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value="/registForm", method=RequestMethod.GET)
	public String registForm() {
		String url = "member/regist";
		return url;
	}

	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws Exception{
		String url = "member/regist_success";

		MemberVO member = memberReq.toMemberVO();
		memberService.regist(member);
		return url;
	}

	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String datail(String id, Model model) throws Exception{
		String url = "member/detail";
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member",member);
		return url;
	}

	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(String id, Model model) throws Exception{
		String url = "member/modify";
		MemberVO member = memberService.getMember(id);
		String picture = MakeFileName.parseFileNameFromUUID(member.getPicture(), "\\$\\$");
		member.setPicture(picture);
		model.addAttribute("member",member);
		return url;
	}

	@Autowired
	MemberRestController restController;

	@RequestMapping(value="/modify", method=RequestMethod.POST,produces="text/plain;charset=utf-8")
	public String modify(MemberModifyCommand modifyReq,HttpSession session, RedirectAttributes rttr)throws Exception {
		String url = "redirect:/member/detail.do";
		MemberVO member = modifyReq.toMember();

		//신규파일변경 및 기존파일삭제
		String oldPicture = memberService.getMember(member.getId()).getPicture();
		if(modifyReq.getUploadPicture()!=null && !modifyReq.getUploadPicture().isEmpty()) {
			String fileName = restController.savePicture(oldPicture, modifyReq.getPicture());
			member.setPicture(fileName);
		}else {
			member.setPicture(oldPicture);
		}

		//DB내용수정
		memberService.modify(member);

		//로그인한 사용자의 경우 수정된 정보로 session업로드
		rttr.addFlashAttribute("parentReload",false);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser!=null && member.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload",true);
		}
		rttr.addAttribute("id",member.getId());
		rttr.addFlashAttribute("from", "modify");
		rttr.addFlashAttribute("member", memberService.getMember(modifyReq.getId()));
		return url;
	}

	@Resource(name="picturePath")
	private String picturePath;

	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String remove(String id, HttpSession session, RedirectAttributes rttr)throws Exception{
		String url = "redirect:/member/detail.do";

		MemberVO member;

		//이미지 파일 삭제
		member = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if(imageFile.exists()) {
			imageFile.delete();
		}

		//DB삭제
		memberService.remove(id);

		//삭제되는 회원이 로그인 회원인 경우 로그아웃 해야함
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser!=null && loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		rttr.addFlashAttribute("removeMember", member);
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("id",id);
		return url;
	}
}
