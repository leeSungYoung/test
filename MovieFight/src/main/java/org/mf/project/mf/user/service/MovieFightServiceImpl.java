package org.mf.project.mf.user.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mf.project.mf.user.dao.MovieFightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;

@Component
@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class MovieFightServiceImpl implements MovieFightService {

	@Autowired
	private MovieFightDao dao;

	@Override
	public void mlist(Model model, Map map) {

		int curPage = 1;
		String nPage = (String) map.get("curPage");
		if (!"".equals(nPage)) {
			curPage = Integer.parseInt(nPage);
		}

		int maxline = 10;

		int start = (maxline * (curPage - 1)) + 1;
		int end = maxline * (curPage);

		int count = dao.count(map);
		int maxPage = count / maxline;
		if (count % maxline > 0) {
			maxPage++;
		}

		map.put("start", start);
		map.put("end", end);

		List list = dao.mlist(map);

		int startPage = 1;
		int endPage = maxPage;

		if (curPage >= 6) {
			startPage = curPage - 5;
		}
		if (maxPage - curPage > 5) {
			endPage = curPage + 5;
		}
		/*
		 * 최대 이전 페이지 5개 //최대 이후 페이지 5개 curpage 가 5 미만일 경우 start pgae = 1 부터
		 * curpage 가 6이상일 경우 curpage -5 부터
		 * 
		 * maxpage - curpage가 5초과라면 endpage = curpage +5 까지 maxpage - curpage가
		 * 5이하라면 endpage = maxpage 까지
		 */

		model.addAttribute("list", list);
		model.addAttribute("searchText", map.get("searchText"));
		model.addAttribute("curPage", curPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}

	@Override
	public void content(Model model, Map map) {
		List list = dao.content(Integer.parseInt((String) map.get("mfKey")));
		// MF 내용 가져오기
		Map mf = (Map) list.get(0);

		String repContent = dao.userRep(map);
		// 개인 댓글 가져오기

		model.addAttribute("mf", mf);
		model.addAttribute("repContent", repContent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String setVote(Map map) {

		List list = dao.content(Integer.parseInt((String) map.get("mfKey")));
		// MF 내용 가져오기
		Map mf = (Map) list.get(0);

		String data = null;

		try {
			SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy. MM. dd");
			// calendar로 바꿀 string의 날짜 형식
			String vote_date = (String) mf.get("mfVDate");
			Calendar mfCal = Calendar.getInstance();
			mfCal.setTime(DateFormat.parse(vote_date));
			// mf에 등록된 투표 마감 날짜

			Calendar sysCal = Calendar.getInstance();
			int year = sysCal.get(sysCal.YEAR);
			int month = sysCal.get(sysCal.MONTH) + 1;// 저장된 month가 +1 된 상태라 맞추기
														// 위해 +1
			int date = sysCal.get(sysCal.DATE);

			sysCal.setTime(DateFormat.parse(year + ". " + month + ". " + date));
			// 시간을 제외한 시스템 날짜

			int dateCheck = sysCal.compareTo(mfCal);
			// 투표기간이 남으면 -1, 당일이면 0, 지났으면 1 반환

			if (dateCheck <= 0) {
				boolean result = haveVote(map);
				if (result) {
					dao.setVote(map);
					data = getVote(map);
				}
			} else {
				data = "dateErr";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public String getVote(Map map) {
		String data = null;

		List vote = dao.getVote(map);
		boolean have = haveVote(map);

		Map voteData = new HashMap();
		voteData.put("have", have);
		voteData.put("vote", vote);

		Gson gson = new Gson();
		data = gson.toJson(voteData);

		return data;
	}

	private boolean haveVote(Map map) {
		// 투표 여부 메서드
		List list = dao.haveVote(map);
		boolean result = true;
		result = list.size() <= 0;
		// 투표했으면 false 안했으면 true
		return result;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String insertRep(Map map) {
		boolean haveVote = haveVote(map);
		// 투표했는지 체크. 안했으면 리턴 err
		String result = "err";
		if (!haveVote) {
			boolean haveReply = haveReply(map);
			// 투표했으면 댓글 썼는지 체크. 기존에 썼으면 update 안썼으면 insert

			if (haveReply) {
				dao.insertRep(map);
				result = "insert";
			} else {
				dao.updateRep(map);
				result = "update";
			}
		}
		return result;
	}

	private boolean haveReply(Map map) {
		// 리플 등록 여부 메서드
		List list = dao.haveRep(map);
		boolean result = true;
		result = list.size() <= 0;
		// 댓글이 있으면 false 안했으면 true
		return result;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String reGood(Map map) {
		boolean selfCheck = selfCheck(map);

		// 기존에 좋아요 했는지 체크
		String result = "self";

		if (selfCheck) {
			// 본인이면 self리턴
			boolean haveGood = haveGood(map);
			result = "have";
			if (haveGood) {
				// 안했으면 insert리턴 //했으면 have 리턴
				dao.reGood(map);
				dao.repGoodup(map);
				result = "insert";
			}
		}
		return result;
	}
	
	private boolean selfCheck(Map map) {
		// 본인 댓글 확인 메서드
		List list = dao.selfCheck(map);
		boolean result;
		result = list.size() <= 0;
		// 본인이면 false 본인이 아니면 true
		return result;
	}

	private boolean haveGood(Map map) {
		// 좋아요 중복 여부 메서드
		List list = dao.haveGood(map);
		boolean result;
		result = list.size() <= 0;
		// 좋아요 했었으면 false 안했으면 true
		return result;
	}

	@Override
	public void reply(Model model, Map map) {
		int curPage = 1;
		String nPage = (String) map.get("curPage");
		if (!"".equals(nPage)) {
			curPage = Integer.parseInt(nPage);
		}

		int maxline = 10;

		int start = (maxline * (curPage - 1));
		int end = maxline * (curPage);

		int count = dao.replyCount(map);
		int maxPage = count / maxline;
		if (count % maxline > 0) {
			maxPage++;
		}

		map.put("start", start);
		map.put("end", end);

		List list = dao.reply(map);

		int startPage = 1;
		int endPage = maxPage;

		if (curPage >= 6) {
			startPage = curPage - 5;
		}
		if (maxPage - curPage > 5) {
			endPage = curPage + 5;
		}
		/*
		 * 최대 이전 페이지 5개 //최대 이후 페이지 5개 curpage 가 5 미만일 경우 start pgae = 1 부터
		 * curpage 가 6이상일 경우 curpage -5 부터
		 * 
		 * maxpage - curpage가 5초과라면 endpage = curpage +5 까지 maxpage - curpage가
		 * 5이하라면 endpage = maxpage 까지
		 */

		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("mfKey", map.get("mfKey"));
		model.addAttribute("mKey", map.get("mKey"));

	}

	@Override
	public String repRefresh(Map map) {

		map.put("order", "left");
		// 첫번째 영화 댓글들
		map.put("sort", "repKey");
		List repList_left = dao.repList(map);
		// 일반 댓글 가져오기

		map.put("sort", "repGood");
		List bestRep_left = dao.repList(map);
		// BEST TOP 3 댓글 가져오기

		map.put("order", "right");
		// 두번째 영화 댓글들
		map.put("sort", "repKey");
		List repList_right = dao.repList(map);
		// 일반 댓글 가져오기

		map.put("sort", "repGood");
		List bestRep_right = dao.repList(map);
		// BEST TOP 3 댓글 가져오기

		Map repLeftObject = new HashMap();
		Map repRightObject = new HashMap();

		repLeftObject.put("mKey", map.get("mKey1"));
		repLeftObject.put("repList_left", repList_left);
		repLeftObject.put("bestRep_left", bestRep_left);

		repRightObject.put("mKey", map.get("mKey2"));
		repRightObject.put("repList_right", repList_right);
		repRightObject.put("bestRep_right", bestRep_right);

		List repList = new ArrayList();
		repList.add(repLeftObject);
		repList.add(repRightObject);

		Gson gson = new Gson();

		String str = gson.toJson(repList);

		return str;
	}
}
