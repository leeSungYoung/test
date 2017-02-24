package org.mf.project.magazine.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mf.project.magazine.user.service.MagazineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/magazine")
public class MagazineController {

	@Autowired
	MagazineServiceInterface magazineService;
	
	// 매거진 페이지.
	@RequestMapping(value = "/{classfy}/{seq}", method = RequestMethod.GET)
	public String magazinePage(Model model, @PathVariable String classfy, @PathVariable String seq) {
						
		int startPage = 0;
		int endPage = 0;
		int page = 0;
		int noticeByPage = 10;	// 한 페이지 안에 들어가는 게시물 개수
		String rowNum = magazineService.getRow();	// 게시물의 전체개수를 db에서 가져온다
		int pageNum = Integer.parseInt(rowNum) / noticeByPage + 1;	// 전체 페이지 수
		if (Integer.parseInt(rowNum) % noticeByPage == 0) {		// 게시물이 noticeByPage 만큼만 있을 경우 다음페이지가 생기지 않도록 -1해준다
			pageNum--;
		}
		
		try {
			
			startPage = (Integer.parseInt(seq) - 1) / 5 * 5 + 1;	// 시작페이지 설정, 현재 6페이지일 경우 (6-1)/5*5+1 = 6 -> 6페이지부터 시작
			endPage = startPage + 5 - 1;

			if (seq != null && seq != "") {
				if (!seq.equals("1")) {		// 첫 페이지가 아닐 경우 그 페이지에 맞는 목록을 가져온다
					int temp = (Integer.parseInt(seq) - 1) * noticeByPage;
					page = temp;
				} else if (seq.equals("1")) {	// 페이지 번호가 1이면  처음부터 noticeByPage 만큼 목록을 가져온다
					page = 0;
				}

			}

			if ( 0 != pageNum && Integer.parseInt(seq) > pageNum) {		// 전체 페이지 수 보다 큰 seq 값이 들어오면 1페이지로 리다이렉트
				return "redirect:/magazine/list/1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/magazine/list/1";	// 이상한 페이지 번호로 들어오면 1페이지로 리다이렉트

		}

		if (endPage > pageNum) {	// 마지막페이지가 7인경우  endPage가 10까지 출력되기 때문에 7페이지로 바꿔줌
			endPage = pageNum;
		}

		List magazineList = new ArrayList();
		
		if (classfy.equals("hitList")){	// {classfy}값에 따라 목록의 정렬 순서를 변경한다.
			magazineList = magazineService.magazineListHitPage(page);	// magHit(조회수) 기준으로  내림차순으로 데이터를 가져온다
		}else{
			magazineList = magazineService.magazineListPage(page);		// magKey(기본 키) 기준으로 내림차순으로 데이터를 가져온다
		}
		
		model.addAttribute("classfy", classfy);
		model.addAttribute("magazineList", magazineList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("start", startPage);
		model.addAttribute("end", endPage);
		model.addAttribute("seq",seq);
		
		return "magazine/list";
	}

	// 매거진 게시물 내용
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void magazineContent(Model model, @RequestParam Map map) throws Exception {
		List magazineContent = magazineService.magazineContent(map);

		model.addAttribute("magazineContent", magazineContent.get(0));
	}


}
