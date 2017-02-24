package org.mf.project.movie.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mf.project.movie.user.service.MovieDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class MovieDetailController {

	@Autowired
	private MovieDetailService service;

	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public String MovieDetail(Model model, @RequestParam Map map) {
		int mKey = Integer.valueOf((String) map.get("mKey"));

		List movie = service.movieDetail(mKey);
		List getAllReply = service.getAllReply(map);
		service.movieInfo(model, map);
		int reSize = getAllReply.size();

		if (movie.size() != 0) {
			Map oneMovie = (Map) movie.get(0);
			String mSummary = (String) oneMovie.get("mSummary");
			String Summary=mSummary.replaceAll("\r\n", "<br>");
			model.addAttribute("mDetail", movie.get(0));
			model.addAttribute("Summary", Summary);
		}

		model.addAttribute("mStar", service.mStar(map));
		model.addAttribute("mZzim", service.mZzim(map));
		model.addAttribute("stillShot", service.seletStillShot(map));
		model.addAttribute("reSize", reSize);
		model.addAttribute("getAllReply", getAllReply);
		model.addAttribute("avgStar", service.getAvgStar(map));

		return "movie/MovieDetail";

	}

	@RequestMapping(value = "/movie/Reply/setReply", method = RequestMethod.POST)
	@ResponseBody
	public int setReply(Model model, @RequestParam Map map, HttpServletRequest req) {

		return service.setReply(map);
	}// 개인덧글 입력

	@RequestMapping(value = "/movie/Good/setGood", method = RequestMethod.GET)
	@ResponseBody
	public String setGood(Model model, @RequestParam Map map) {

		return service.setGood(map);
	}// 덧글 좋아요 입출력

	@RequestMapping(value = "/movie/star/setStar", method = RequestMethod.GET)
	@ResponseBody
	public String setStar(Model model, @RequestParam Map map) {

		return service.setStar(map);
	}// 별점 입출력

	@RequestMapping(value = "/movie/Zzim/setZzim", method = RequestMethod.GET)
	@ResponseBody
	public int setZzim(Model model, @RequestParam Map map) {

		return service.zzim(map);
	}// 찜 입출력

	@RequestMapping(value = "/movie/reply/moreCallIn", method = RequestMethod.GET)
	@ResponseBody
	public List moreCallIn(Model model, @RequestParam Map map) {
		
		return service.moreCallIn(map);
	}// 덧글 출력

	@RequestMapping(value = "/movie/reply/getReply", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getReply(Model model, @RequestParam Map map) {

		return service.getReply(map);
	}// 개인덧글 출력

	@RequestMapping(value = "/movie/MovieList", method = RequestMethod.GET)
	public String MovieMovieList(Model model, @RequestParam Map map) {
		int page = 0;
		if (map.get("page") != null) {
			page = Integer.valueOf((String) map.get("page"));
		}
		int seq = (10 * page); // db로 넘겨줄값,불러올 시작값
		map.put("seq", seq);
		service.getMovieList(model, map);
		int curPage = page + 1;// 현재 패이지 초기화값이 0부터임으로+1
		int totalCnt = service.pageing(map);// 전체 게시글 수
		int totalPage = (totalCnt / 10);// 전체 페이지 수
		int restPage = (totalCnt % 10);// 남는 게시글 수

		int stPage = 1;
		int endPage = stPage + 4;

		if (totalCnt % 10 != 0) {
			totalPage++;
		}

		if (endPage == page) {
			stPage += page;
			endPage = stPage + 2;
		}
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		model.addAttribute("restPage", restPage);

		model.addAttribute("stPage", stPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("endPage", endPage);

		return "movie/MovieList";
	}// 영화 리스트 페이징 처리

}