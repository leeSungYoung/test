package org.mf.project.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mf.project.home.service.HomeService;
import org.mf.project.movie.user.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	private HomeService service;

	@Autowired
	private MovieServiceInterface movieService;

	@RequestMapping("/")
	public String home(Model model) {
		//메인 페이지
		Map map = new HashMap();

		service.index(model);
		// mf 최근 게시글 3개 불러오기

		movieService.boxOffice(model);
		// 박스 오피스 & 영화 정보 불러오기

		return "index";
	}
	
	@RequestMapping(value="/error")
	public String error(){
		return "err/err_default";
	}
}
