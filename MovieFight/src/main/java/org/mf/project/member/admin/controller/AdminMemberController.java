package org.mf.project.member.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mf.project.member.admin.service.AdminMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMemberController {

	@Autowired
	private AdminMemberService service;
	
	@RequestMapping(value="/member/list",method=RequestMethod.GET)
	public String list(){
		//페이지를 안받는 경우 forward
		return "forward:/member/list/1";
	}
	
	@RequestMapping(value="/member/list/{curPage}",method=RequestMethod.GET)
	public String list(Model model,HttpServletRequest request, @PathVariable String curPage){
		//멤버 리스트 페이지
		Map map = new HashMap();
		map.put("curPage", curPage);
		service.list(model,map);
		
		return "member/list";
	}
}
