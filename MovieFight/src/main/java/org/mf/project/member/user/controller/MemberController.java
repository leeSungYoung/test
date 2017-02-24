package org.mf.project.member.user.controller;

import java.security.Provider.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mf.project.member.user.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	// 로그인 폼 
	@RequestMapping("/member/login1")
	public String login1_NL() {

		return "member/login";
	}

	// 로그인처리 
	@RequestMapping(value = "/member/login2", method = RequestMethod.POST)
	public String lonin2_NL(HttpServletRequest req, Model model, @RequestParam Map map,RedirectAttributes red) {

		String resultURI = memberService.login2(req, model, map, red);

		return resultURI;

	}
	


	// 회원가입 폼
	@RequestMapping(value = "/member/joinStep1")
	public String joinStep1_NL() {

		return "member/joinStep";
	}

	// 회원가입 처리 
	@RequestMapping(value = "/member/joinStep2", method = RequestMethod.POST)
	public String joinStep2_NL(@RequestParam Map map) {

		memberService.joinStep2(map);

		return"redirect:/"; //가입완료후 메인페이지

	}

	// 아이디 중복체크 
	@RequestMapping(value = "/member/checkidDup", method = RequestMethod.POST)
	@ResponseBody
	public  int checkidDup_NL(@RequestParam Map map) {

		int i = memberService.checkidDup(map);

		return i;
	}

	// 탈퇴폼
	@RequestMapping(value = "/member/delete1", method = RequestMethod.GET)
	public String delete1_ML(Locale locale, Model model, HttpServletRequest req) {

		return "member/delete";
	}

	// 탈퇴처리 
	@RequestMapping(value = "member/delete2")
	public String delete2_ML(HttpServletRequest request) {

		String resultURI = null;
		memberService.delete2(request);

		resultURI = "forward:/member/logout";  //탈퇴 완료시 로그아웃 메서드로 이동
		
		return resultURI;
	}

	// 로그아웃
		@RequestMapping(value = "/member/logout")
		public String logout_ML(HttpServletRequest req) {
			memberService.logout(req);

			return "redirect:/"; //로그아웃시 메인페이지로 이동

		}
	// 회원정보수정 비밀번호 체크 폼
	@RequestMapping(value = "member/checkPW1")
	public String checkPW1_ML(Locale locale, Model model, HttpServletRequest req) {

		return "member/checkPW";

	}

	// 회원정보수정 비밀번호 체크 처리
	@RequestMapping(value = "member/checkPW2")
	public String checkPW2_ML(HttpServletRequest req, Locale locale, Model model, @RequestParam Map map) {

		
		
		return "redirect:/";

	}

	
		
		

		// 정보수정 비밀번호 통과 후 기본정보 수정폼
		@RequestMapping(value = "member/update1")
		public String update1_ML(Model model, HttpServletRequest req, @RequestParam Map map,RedirectAttributes red) {
			
			HttpSession session = req.getSession();
			String url = "redirect:/";
			boolean check = memberService.update1(map, req, model);
			
			if (null != session.getAttribute("memKey")) { // 기본정보 수정을 위한 비밀번호 체크 페이지 비밀번호 일치 여부 확인

				if (check) {
					url = "member/update"; //일치시 업데이트 페이지

				} else {
					
					red.addFlashAttribute("MSGPW", "패스워드를 확인하세요");
					//req.setAttribute("MSGPW", "패스워드를 확인하세요");
					url = "redirect:checkPW1"; //불일치시 경고문구
				}
			}
			
			return url;
		}
		
	// 기본정보 수정 처리
	@RequestMapping(value = "member/update2", method = RequestMethod.POST)
	public String update2_ML(HttpServletRequest request, @RequestParam Map map) {

		memberService.update2(request, map);

		return "redirect:/"; //기본정보 수정 완료 후 메인페이지로 이동
	}

	// 정보수정 비밀번호 통과 후 비밀번호 변경 폼
			@RequestMapping(value = "member/updatePW1")
			public String updatePW1_ML(Model model, HttpServletRequest req, @RequestParam Map map,RedirectAttributes red) {

				HttpSession session = req.getSession();
				String url = "redirect:/";
				boolean check = memberService.updatePW1(map, req, model);
				
				if (null != session.getAttribute("memKey")) { // 비밀번호 변경을 위한 비밀번호 체크 페이지 비밀번호 일치 여부 확인

					if (check) {
						url = "member/updatePW"; //일치시 비밀번수 수정 페이지로 이동

					} else {
						red.addFlashAttribute("MSGPW2",  "패스워드를 확인하세요");
						// req.setAttribute("MSGPW2", "패스워드를 확인하세요"); //불일치 경고 메시지
						url = "redirect:checkPW1";
					}
				}
				
				return url;
			}
	// 정보수정 비밀번호 통과 후 비밀번호 변경 처리
			@RequestMapping(value = "member/updatePW2")
			public String updatePW2_ML(HttpServletRequest request, @RequestParam Map map) {

				memberService.updatePW2(request, map);
				return "redirect:/"; //변경 완료후 메인페이지로 이동
			}

	// 비밀번호 찾기후 비밀번호 변경 폼
	@RequestMapping(value = "member/updatePW3")
	public String updatePW3_NL(HttpServletRequest req) {
		
		return "member/updatePW2";
	}
		
	// 비밀번호 찾기후 비밀번호 변경 처리
	@RequestMapping(value = "member/updatePW3", method = RequestMethod.POST)
	public String updatePW4_NL(Model model, @RequestParam Map map) {
		memberService.updatePW3(map);

		
		

		return "redirect:/"; //변경 완료후 메인페이지로 이동
	}
	

	// 비로그인 상태 비밀번호 찾기 폼(정보 입력창)
	@RequestMapping(value = "member/findPW1")
	public String findPW1_NL(Locale locale, Model model, HttpServletRequest req) {

		return "member/findPW";

	}
	// 비로그인 상태 비밀번호 찾기 처리(정보 입력창)
	@RequestMapping(value = "member/findPW2", method = {RequestMethod.GET,RequestMethod.POST})
	public String findPW2_NL(HttpServletRequest req, Model model, @RequestParam Map map,RedirectAttributes red) {
		String resultURI = memberService.findPW2(req, model, map, red);
	

		return resultURI;

	}


	// 영화 별점 폼
	@RequestMapping(value = "member/star")
	public String star_ML(Model model, @RequestParam Map map, HttpServletRequest req) {

		List selectStar = memberService.star(req, map);
		int lineNum = selectStar.size() / 2;
		if (selectStar.size() % 2 > 0) { //화면에서 리스트의 인덱스 번호별로 순차적으로 배치
			lineNum++;
		}
		model.addAttribute("selectStar", selectStar);
		model.addAttribute("lineNum",lineNum);

		return "member/star";

	}

	// 영화 찜 폼
	@RequestMapping(value = "member/zzimMovie")
	public String zzimMovie_ML(Model model, @RequestParam Map map, HttpServletRequest req) {

		List zzimM = memberService.zzimMovie(req, map);

		int lineNum = zzimM.size() / 2;
		if (zzimM.size() % 2 > 0) { //화면에서 리스트의 인덱스 번호별로 순차적으로 배치
			lineNum++;
		}
		
		model.addAttribute("zzimM", zzimM);
		model.addAttribute("lineNum",lineNum);

		return "member/zzimMovie";
	}

}
