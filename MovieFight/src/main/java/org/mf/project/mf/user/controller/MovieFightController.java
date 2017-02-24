package org.mf.project.mf.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mf.project.mf.user.service.MovieFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieFightController {

	@Autowired
	private MovieFightService service;

	@RequestMapping(value = "/mf/list")
	public String mlist() {
		//페이지를 안받는경우 forward
		return "forward:/mf/list/1";
	}

	@RequestMapping(value = "/mf/list/{curPage}", method = RequestMethod.GET)
	public String mlist(Model model, @PathVariable String curPage, @RequestParam Map map) {
		//리스트 페이지
		map.put("curPage", curPage);

		service.mlist(model, map);

		return "mf/mlist";
	}

	@RequestMapping(value = "/mf/content/{mfKey}", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model, @PathVariable String mfKey) {
		//게시글 보기 페이지
		Object memKeyS = request.getSession().getAttribute("memKey");
		int memKey = 0;
		if (memKeyS != null) {
			memKey = (int)memKeyS;
		}
		Map map = new HashMap();
		map.put("mfKey", mfKey);
		map.put("memKey", memKey);
		
		service.content(model, map);

		return "mf/content";
	}

	@RequestMapping(value = "/mf/setvote/{mfKey}/{mKey}", method = RequestMethod.GET)
	@ResponseBody
	public String setVote(HttpServletRequest request, @PathVariable String mfKey, @PathVariable String mKey) {
		//투표 Ajax 페이지
		Object memKeyS = request.getSession().getAttribute("memKey");
		int memKey = 0;
		if (memKeyS != null) {
			memKey = (int)memKeyS;
		}else{
			return "err";
			//비로그인 err리턴
		}

		Map map = new HashMap();
		map.put("mfKey", mfKey);
		map.put("mKey", mKey);
		map.put("memKey", memKey);
		
		String result = service.setVote(map);

		return result;
	}

	@RequestMapping(value = "/mf/getvote/{mfKey}", method = RequestMethod.GET)
	@ResponseBody
	public String getVote(HttpServletRequest request, @PathVariable int mfKey) {
		//투표 결과 및 본인의 투표 여부 Ajax 페이지
		Object memKeyS = request.getSession().getAttribute("memKey");
		int memKey = 0;
		if (memKeyS != null) {
			memKey = (int)memKeyS;
		}

		Map map = new HashMap();
		map.put("mfKey", mfKey);
		map.put("memKey", memKey);
		 
		String result = service.getVote(map);

		return result;
	}
	
	
	@RequestMapping(value="/mf/reply/insert",method=RequestMethod.POST)
	@ResponseBody
	public String insert(HttpServletRequest request, @RequestParam Map map){
		//댓글 입력 Ajax 페이지
		int memKey = (int)request.getSession().getAttribute("memKey");
		String memName = (String) request.getSession().getAttribute("memName");
		map.put("memKey", memKey);
		map.put("memName", memName);
		
		String result = service.insertRep(map);
		//insert면 새로 등록 or update면 수정 err면 투표안함
		
		return result;
	}
	
	@RequestMapping(value="/mf/good",method=RequestMethod.POST)
	@ResponseBody
	public String reGood(HttpServletRequest request, @RequestParam Map map){
		//댓글 좋아요 Ajax 페이지
		Object memKeyS = request.getSession().getAttribute("memKey");
		int memKey = 0;
		if (memKeyS != null) {
			memKey = (int)memKeyS;
		}else{
			return "err";
		}
		map.put("memKey", memKey);
		
		String result = service.reGood(map);
		
		
		return result;
	}
	
	@RequestMapping(value="/mf/reply/{mfKey}/{mKey}",method=RequestMethod.GET)
	public String reply(@PathVariable("mfKey") String mfKey,@PathVariable("mKey") String mKey){
		//댓글 리스트 페이지. 페이지 정보가 없으면 forward
		return "forward:/mf/reply/"+mfKey+"/"+mKey+"/1";
	}
	
	@RequestMapping(value="/mf/reply/{mfKey}/{mKey}/{curPage}",method=RequestMethod.GET)
	public String reply(Model model,@PathVariable("mfKey") String mfKey,@PathVariable("mKey") String mKey,@PathVariable("curPage") String curPage,@RequestParam Map map){
		//댓글 리스트 페이지
		map.put("mfKey", mfKey);
		map.put("mKey", mKey);
		map.put("curPage", curPage);
		
		service.reply(model, map);
		
		return "mf/MovieFightReply";
	}
	
	@RequestMapping(value="/mf/content/reply/list",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String rep_Refresh(@RequestParam Map map){
		
		return service.repRefresh(map);
	}

}
