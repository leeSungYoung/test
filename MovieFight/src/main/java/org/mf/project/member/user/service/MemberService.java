package org.mf.project.member.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface MemberService {


	public String login2(HttpServletRequest req, Model model, Map map, RedirectAttributes red); //로그인
		
	public int joinStep2(Map map); //회원가입

	public int delete2(HttpServletRequest request); //회원탈퇴
	
	public boolean updatePW1(Map map, HttpServletRequest req, Model model); // 비밀번호 수정 전 비밀번호 체크
	
	public int updatePW2(HttpServletRequest request,Map map); //비밀번호 수정
	
	public boolean update1(Map map, HttpServletRequest request,Model model); //기본정보 수정 페이지 
	
	public int update2(HttpServletRequest request,Map map); //기본정보 수정
	
	public int checkidDup(Map map); //아이디 중복확인

	public String findPW2(HttpServletRequest req, Model model,Map map, RedirectAttributes red); //비밀번호 찾기

	public int updatePW3(Map map); // 비밀번호 찾기 후 비밀번호 수정

	public void logout(HttpServletRequest req); //로그아웃

	public List star(HttpServletRequest request, Map map); //영화 평가 리스트

	public List zzimMovie(HttpServletRequest request, Map map); //영화 찜 리스트

	

	
	

	

	

	
	
	

}
