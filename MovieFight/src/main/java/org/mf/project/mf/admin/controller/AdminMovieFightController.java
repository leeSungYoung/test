package org.mf.project.mf.admin.controller;

import java.util.Map;

import org.mf.project.mf.admin.service.AdminMovieFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminMovieFightController {

	@Autowired
	private AdminMovieFightService service;

	@RequestMapping(value = "/mf/insert", method = RequestMethod.GET)
	public String insert() {
		// 입력 폼 페이지
		return "mf/MovieFightInsert";
	}

	@RequestMapping(value = "/mf/insert", method = RequestMethod.POST)
	public String insert(Model model, @RequestParam Map map) {
		// 입력 폼 처리 페이지
		service.insert(map);

		return "redirect:/mf/list/1";
	}

	@RequestMapping(value = "/mf/update/{mfKey}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable String mfKey) {
		// 수정 폼 페이지
		service.content(model, mfKey);

		return "mf/MovieFightUpdate";
	}

	@RequestMapping(value = "/mf/update", method = RequestMethod.POST)
	public String update(@RequestParam Map map) {
		// 수정 처리 페이지
		service.update(map);

		return "redirect:/mf/content/" + map.get("mfKey");
	}
	
	@RequestMapping(value="/mf/delete/{mfKey}",method=RequestMethod.GET)
	public String delete(@PathVariable String mfKey){
		
		service.delete(mfKey);
		
		return "redirect:/mf/list";
	}
	

	@RequestMapping(value = "/mf/insert/search", method = RequestMethod.GET)
	public String search() {

		return "forward:/mf/insert/search/1";
	}
	
	@RequestMapping(value = "/mf/insert/search/{curPage}", method = RequestMethod.GET)
	public String search(Model model, @RequestParam Map map,@PathVariable String curPage) {
		// 영화 찾기 페이지
		map.put("curPage", curPage);
		
		service.search(model, map);

		return "mf/MovieFightSearch";
	}
}
