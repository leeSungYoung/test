package org.mf.project.mf.admin.service;

import java.util.List;
import java.util.Map;

import org.mf.project.mf.admin.dao.AdminMovieFightDao;
import org.mf.project.mf.user.dao.MovieFightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Component
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class AdminMovieFightServiceImpl implements AdminMovieFightService {

	@Autowired
	private AdminMovieFightDao dao;
	
	@Autowired
	private MovieFightDao mfDao;

	@Override
	public int insert(Map map) {
		return dao.insert(map);
	}
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void content(Model model, String mfKey) {
		
		List list = mfDao.content(Integer.parseInt(mfKey));
		
		model.addAttribute("mf",(Map)list.get(0));
		
	}
	
	@Override
	public int update(Map map) {
		
		return dao.update(map);
	}
	
	@Override
	public void delete(String str_mfKey) {
		int mfKey = Integer.parseInt(str_mfKey);
		
		dao.deleteReply(mfKey);
		//댓글 삭제
		
		dao.deleteVote(mfKey);
		//투표 삭제
		
		dao.deleteMF(mfKey);
		//mf 삭제
		
	}
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int search(Model model, Map map) {

//		String searchWord = (String) map.get("searchWord");
		//검색 단어
		int curPage = 1;
		String nPage = (String) map.get("curPage");
		if(!"".equals(nPage)){
			curPage = Integer.parseInt(nPage);
		}
		
		int maxline = 10;
		//페이지당 게시글 갯수
		
		int start = (maxline*(curPage-1))+1;
		int end = maxline*(curPage);
		
		int count = dao.count(map);
		//총 영화 갯수
		int maxPage = count/maxline;
		if(count%maxline > 0){
			maxPage++;
		}
		
//		Map data = new HashMap();
//		data.put("searchWord", searchWord);
		map.put("start", start);
		map.put("end", end);
		
		List list = dao.search(map);

		
		int startPage = 1;
		int endPage = maxPage;
		
		if(curPage >= 6){
			startPage = curPage - 5;
		}
		if(maxPage - curPage>5){
			endPage = curPage + 5;
		}
		/* 최대 이전 페이지 5개 //최대 이후 페이지 5개
		 * curpage 가 5 미만일 경우 start pgae = 1 부터
		 * curpage 가 6이상일 경우 curpage -5 부터
		 * 
		 * maxpage - curpage가 5초과라면 endpage = curpage +5 까지
		 * maxpage - curpage가 5이하라면 endpage = maxpage 까지
		*/
		
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("searchWord" , map.get("searchWord"));

		return 0;

	}
}
