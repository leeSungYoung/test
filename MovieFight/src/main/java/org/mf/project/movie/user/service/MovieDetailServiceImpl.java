package org.mf.project.movie.user.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mf.project.movie.user.dao.MovieDtailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class MovieDetailServiceImpl implements MovieDetailService {

	@Autowired
	private MovieDtailDao movieDtailDao;

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List movieDetail(int mKey) {
		return movieDtailDao.movieDetail(mKey);
	}// 영화정보 출력

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int setReply(Map map) {
		int a = 0;
		if ("" != map.get("memKey")) {// 로그인 유뮤
			List star = movieDtailDao.getStar(map);
			if (star.size() != 0) {// 등록되어있는 별점 유무
				List getReply = movieDtailDao.getReply(map);

				if (getReply.size() != 0) {// 등록되어있는 리플 유무
					movieDtailDao.upReply(map);
					return a = 2;// 있으면 업데이트
				} else {
					movieDtailDao.setReply(map);
					return a = 1;// 없으면 등록
				}
			}
			a = 3;
		}
		return a;
	}// 로그인 확인 & 개인덧글 입력수정

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String getReply(Map map) {
		// TODO Auto-generated method stub

		List getReply = movieDtailDao.getReply(map);
		if (getReply.size() != 0) {
			Map reply = (Map) getReply.get(0);
			return (String) reply.get("reContent");

		}
		return "";
	}// 개인 덧글 출력

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int upReply(Map map) {
		// TODO Auto-generated method stub
		return movieDtailDao.upReply(map);
	}// 개인 리플 수정

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List getAllReply(Map map) {
		// TODO Auto-generated method stub

		return movieDtailDao.getAllReply(map);
	}// 등록된 덧글 출력

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String setGood(Map map) {
		// TODO Auto-generated method stub

		if ("" != map.get("memKey")) {
			// 로그인 체크

			List getGoodName = movieDtailDao.getGoodName(map);
			int checkGood = movieDtailDao.checkGood(map);
			int crMemKey = Integer.valueOf((String)map.get("memKey")) ;
			if (checkGood!=crMemKey) {
				if (getGoodName.size() == 0) {
					// 등록된 좋아요 아이디 체크

					movieDtailDao.setGood(map);
					movieDtailDao.setGoodName(map);
					return String.valueOf(movieDtailDao.getGood(map));

				}

				return "overlap";

			}

			return "myRep";
		}
		return "missing";
	}// 좋아요 등록&체크

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int getGood(Map map) {
		// TODO Auto-generated method stub
		return movieDtailDao.getGood(map);
	}// 좋아요 출력

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String setStar(Map map) {
		// TODO Auto-generated method stub
		String a = "missing";
		if ("" != map.get("memKey")) {
			List getStar = movieDtailDao.getStar(map);
			if (getStar.size() != 0) {
				movieDtailDao.upStar(map);
				List getStar2 = movieDtailDao.getStar(map);
				Map getOneStar2 = (Map) getStar2.get(0);
				a = String.valueOf(getOneStar2.get("star"));
			} else {
				movieDtailDao.setStar(map);
				List getStar2 = movieDtailDao.getStar(map);
				Map getOneStar2 = (Map) getStar2.get(0);
				a = String.valueOf(getOneStar2.get("star"));
			}

			return a;

		}
		return a;

	}// 별점 등록&체크

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int zzim(Map map) {
		// TODO Auto-generated method stub
		int a = 0;
		if ("" != map.get("memKey")) {
			List getZzim = movieDtailDao.getZzim(map);
			if (getZzim.size() != 0) {
				Map oneZzime = (Map) getZzim.get(0);
				int zzim = (int) oneZzime.get("zKey");
				movieDtailDao.delZzim(zzim);
				a = 2;
			} else {
				movieDtailDao.setZzim(map);

				a = 1;
			}

		}
		return a;
	}// 찜 등록&체크

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List seletStillShot(Map map) {
		// TODO Auto-generated method stub
		List stil = movieDtailDao.seletStillShot(map);

		if (stil.size() == 0) {
			map.put("fileModify", "/resources/images/common/no_image_found.jpg");
			stil.add(0, map);

			return stil;
		}

		return stil;
	}// 스틸샷 출력

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List getStar(Map map) {
		// TODO Auto-generated method stub
		return movieDtailDao.getStar(map);
	}// 별점 출력

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List moreCallIn(Map map) {
		// TODO Auto-generated method stub
		map.put("stNum", Integer.parseInt((String) map.get("stNum")));

		return movieDtailDao.moreCallIn(map);
	}// 전체 덧글 출력

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void movieInfo(Model model, Map map) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";
		url += "key=7f0a596079236944b463acb1c3d7baf7";
		url += "&movieCd=" + map.get("mKey");
		// 정보를 받은 get방식 url

		URI uri = URI.create(url);
		String responseString = restTemplate.getForObject(uri, String.class);
		// json을 받아옴.

		Gson gson = new Gson();
		Map responseMap = gson.fromJson(responseString, HashMap.class);
		Map resultMap = (Map) responseMap.get("movieInfoResult");
		// json object를 맵으로
		Map movieInfo = (Map) resultMap.get("movieInfo");
		List audits = (List) movieInfo.get("audits");
		String watchGradeNm = "심의등급 없음";
		if (audits.size() != 0) {
			Map audits0 = (Map) audits.get(0);
			watchGradeNm = (String) audits0.get("watchGradeNm");
		} // 정보가 비어있는 경우가 있을 경우 처리
		List actors = (List) movieInfo.get("actors");
		String movieNmEn = (String) movieInfo.get("movieNmEn");
		String prdtYear = (String) movieInfo.get("prdtYear");
		List genreNm = (List) movieInfo.get("genres");
		String showTm = (String) movieInfo.get("showTm");
		List nations = (List) movieInfo.get("nations");
		String movieName = (String) movieInfo.get("movieNm");
		if (nations.size() != 0) {
			Map oneNations = (Map) nations.get(0);
			String nationNm = (String) oneNations.get("nationNm");
			model.addAttribute("nationNm", nationNm);
		} // 정보가 비어있는 경우가 있을 경우 처리
		List directors = (List) movieInfo.get("directors");
		model.addAttribute("movieNm", movieName);
		model.addAttribute("actors", actors);
		model.addAttribute("movieNmEn", movieNmEn);
		model.addAttribute("prdtYear", prdtYear);
		model.addAttribute("genres", genreNm);
		model.addAttribute("watchGradeNm", watchGradeNm);
		model.addAttribute("showTm", showTm);
		model.addAttribute("directors", directors);

	}// 영화 api

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int mStar(Map map) {
		// TODO Auto-generated method stub
		return movieDtailDao.mStar(map);
	}// 별점 등록 갯수 출력

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int mZzim(Map map) {
		// TODO Auto-generated method stub
		return movieDtailDao.mZzim(map);
	}// 찜등록갯수

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String getAvgStar(Map map) {
		// TODO Auto-generated method stub
		String star = movieDtailDao.getAvgStar(map);
		if (star == null) {

			return "0";
		}

		return star.substring(0, 3);

	}// 해당영화 평균 별점 출력

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void getMovieList(Model model, Map map) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		List getMovieList = movieDtailDao.getMovieList(map);

		List MovieList = new ArrayList();

		for (int i = 0; i < getMovieList.size(); i++) {
			Map getMovie = (Map) getMovieList.get(i);
			String mKey = String.valueOf(getMovie.get("mKey"));

			String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";
			url += "key=7f0a596079236944b463acb1c3d7baf7";
			url += "&movieCd=" + mKey;
			// 정보를 받은 get방식 url
			URI uri = URI.create(url);
			String responseString = restTemplate.getForObject(uri, String.class);
			// json을 받아옴.
			Gson gson = new Gson();
			Map responseMap = gson.fromJson(responseString, HashMap.class);
			Map resultMap = (Map) responseMap.get("movieInfoResult");
			// json object를 맵으로
			Map movieInfo = (Map) resultMap.get("movieInfo");
			List actors = (List) movieInfo.get("actors");// 배우
			String prdtYear = (String) movieInfo.get("prdtYear");// 제작년도
			List genreNm = (List) movieInfo.get("genres"); // 장르
			String showTm = (String) movieInfo.get("showTm");// 상영시간
			List nations = (List) movieInfo.get("nations");
			List directors = (List) movieInfo.get("directors");// 감독

			if (nations.size() != 0) {
				Map oneNations = (Map) nations.get(0);
				getMovie.put("nationNm", oneNations.get("nationNm"));
			} else {
				getMovie.put("nationNm", null);
			} // 정보가 비어있는 경우가 있을 경우 처리
			getMovie.put("actors", actors);
			getMovie.put("prdtYear", prdtYear);
			getMovie.put("genres", genreNm);
			getMovie.put("showTm", showTm);
			getMovie.put("directors", directors);
			MovieList.add(i, getMovie);
		}
		model.addAttribute("getMovieList", MovieList);
	}// 영화리스트 정보

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int pageing(Map map) {
		// TODO Auto-generated method stub

		return movieDtailDao.getTotalMoveCnt(map);
	}// 페이징

}
