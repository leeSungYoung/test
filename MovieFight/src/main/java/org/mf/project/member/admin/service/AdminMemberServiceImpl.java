package org.mf.project.member.admin.service;

import java.util.List;
import java.util.Map;

import org.mf.project.member.admin.dao.AdminMemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Component
@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class AdminMemberServiceImpl implements AdminMemberService {

	@Autowired
	private AdminMemberDao dao;
	
	@Override
	public void list(Model model, Map map) {
		
		int curPage = 1;
		String nPage = (String) map.get("curPage");
		if(!"".equals(nPage)){
			curPage = Integer.parseInt(nPage);
		}
		int maxline = 10;
		//페이지당 게시글 갯수
		
		int start = (curPage-1)*maxline+1;
		int end = curPage*maxline;
		//보여줄 페이지의 시작 게시글 번호, 끝 게시글 번호
		
		int count = dao.count();
		//총 회원 수
		int maxPage = count/maxline;
		if(count%maxline > 0){
			maxPage++;
			//회원수가 10의 배수가 아니면 +1
		}
		map.put("start", start);
		map.put("end", end);
		
		List list = dao.list(map);
		
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
		
		model.addAttribute("list",list);
		model.addAttribute("curPage",curPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
	}

}
