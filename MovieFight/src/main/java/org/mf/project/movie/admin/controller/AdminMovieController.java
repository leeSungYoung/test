package org.mf.project.movie.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mf.project.movie.admin.service.AdminMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminMovieController {

	@Autowired
	private AdminMovieService service;

	@RequestMapping(value = "/movie/insert", method = RequestMethod.GET)
	public String insert() {
		// 입력 폼 페이지
		return "movie/MovieInsert";
	}

	@RequestMapping(value = "/movie/insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, @RequestParam Map map,
			@RequestParam("poster") MultipartFile poster, @RequestParam("upload") MultipartFile[] upload) {
		// 입력 처리 페이지
		service.insert(request, map, poster, upload);

		return "redirect:/movie/MovieList";
	}

	@RequestMapping(value = "/movie/update/{mKey}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable int mKey) {
		// 수정 폼 페이지
		service.update(model, mKey);
		return "movie/MovieUpdate";
	}

	@RequestMapping(value = "/movie/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @RequestParam Map map,
			@RequestParam("poster") MultipartFile poster, @RequestParam("upload") MultipartFile[] upload,
			@RequestParam("fileKey") String[] fileKey, @RequestParam("fileName") String[] fileName) {
		// 수정 처리 페이지
		service.update(request, map, poster, upload, fileKey, fileName);
		return "redirect:/movie?mKey="+map.get("mKey");
	}

	@RequestMapping(value = "/movie/delete/{mKey}", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, @PathVariable int mKey) {
		// 삭제 처리 페이지
		service.delete(request, mKey);

		return "redirect:/movie/MovieList";
	}

	@RequestMapping(value = "/movie/insert/search", method = RequestMethod.GET)
	public String search(Model model, @RequestParam Map map) {
		// 영화 검색 페이지

		service.search(model, map);

		return "movie/MovieSearch";
	}

}
