package org.mf.project.storage;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class BoxOffice {

	private static List boxOffice = null;

	private static String recently_Day;

	private static List getBoxOffice() {
		return boxOffice;
	}

	private static void setBoxOffice(List boxOffice) {
		BoxOffice.boxOffice = boxOffice;
	}

	public static String getRecently_Day() {
		return recently_Day;
	}

	private static void setRecently_Day(String recently_Day) {
		BoxOffice.recently_Day = recently_Day;
	}

	public static void updateBoxOffice() {

		RestTemplate restTemplate = new RestTemplate();
		Date now = new Date();
		Date yesterday = new Date();

		// yesterday의 날짜를 현재시각에서 24시간 전으로 바꿔준다
		yesterday.setTime(now.getTime() - (1000 * 60 * 60 * 24));

		// 날짜형식을 정해준다
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		setRecently_Day(sdf.format(yesterday));

		// 받아올 json url 입력
		String boxOfficeUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?";
		boxOfficeUrl += "key=3dd68bbad0c1de730a4da13b0c87a702";
		boxOfficeUrl += "&targetDt=" + getRecently_Day();

		// json을 문자열로 받는다
		URI boxOfficeUri = URI.create(boxOfficeUrl);
		String boxResponseString = restTemplate.getForObject(boxOfficeUri, String.class);

		// json을 parsing
		Gson gson = new Gson();
		Map boxResponserMap = gson.fromJson(boxResponseString, HashMap.class);
		Map boxResultMap = (Map) boxResponserMap.get("boxOfficeResult");

		List runningMovieList = (List) boxResultMap.get("dailyBoxOfficeList");

		List newMovieList = new ArrayList();
		for (int i = 0; i < runningMovieList.size(); i++) {
			Map runningMovieMap = (Map) runningMovieList.get(i);

			String MovieCd = (String) ((Map) runningMovieList.get(i)).get("movieCd");
			String infoUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";
			infoUrl += "key=3dd68bbad0c1de730a4da13b0c87a702";
			infoUrl += "&movieCd=" + runningMovieMap.get("movieCd");
			// 정보를 받은 get방식 url

			URI infoUri = URI.create(infoUrl);
			String infoResponseString = restTemplate.getForObject(infoUri, String.class);
			// json을 받아옴.

			Map infoResponseMap = gson.fromJson(infoResponseString, HashMap.class);
			Map infoResultMap = (Map) infoResponseMap.get("movieInfoResult");
			// json object를 맵으로
			Map movieInfo = (Map) infoResultMap.get("movieInfo");

			List actors = (List) movieInfo.get("actors");
			List genres = (List) movieInfo.get("genres");
			List directors = (List) movieInfo.get("directors");
			String showTm = (String) movieInfo.get("showTm");

			runningMovieMap.put("actors", actors);
			runningMovieMap.put("genres", genres);
			runningMovieMap.put("directors", directors);
			runningMovieMap.put("showTm", showTm);

			newMovieList.add(i, runningMovieMap);
		}

		setBoxOffice(newMovieList);
	}

	public static List getRefreshList() {
		if (getBoxOffice() == null) {
			BoxOffice.updateBoxOffice();
		}
		return getBoxOffice();

	}
}
