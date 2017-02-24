package org.mf.project.movie.admin.service;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mf.project.movie.admin.dao.AdminMovieDao;
import org.mf.project.movie.user.dao.MovieDtailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@Component
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class AdminMovieServiceImpl implements AdminMovieService {

	@Autowired
	private AdminMovieDao dao;

	@Autowired
	private MovieDtailDao movieDtailDao;

	@Override
	public int insert(HttpServletRequest request, Map map, MultipartFile poster, MultipartFile[] upload) {

		int result = dao.insert(map);
		// 입력 정보 저장
		int mKey = Integer.parseInt((String) map.get("mKey"));

		if (result > 0) {
			// DB입력에 성공하면 파일 첨부 처리

			posUpload(request, mKey, poster);
			// 포스터 업로드

			for (MultipartFile file : upload) {
				ssUpload(request, mKey, file);
				// 스틸샷 업로드
			}

		}
		return 0;
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void update(Model model, int mKey) {
		List list = movieDtailDao.movieDetail(mKey);
		// 포스터와 스틸샷은 가져오지 않음.
		Map movie = (Map) list.get(0);
		// 영화 정보

		List poster = movieDtailDao.seletPoster(movie);
		// 포스터 정보

		List StillShot = movieDtailDao.seletStillShot(movie);
		// 영화 스틸샷 정보

		List mTrailer = new ArrayList();
		mTrailer.add(movie.get("mTrailer1"));
		mTrailer.add(movie.get("mTrailer2"));
		mTrailer.add(movie.get("mTrailer3"));

		model.addAttribute("movie", movie);
		if (poster.size() > 0) {
			model.addAttribute("poster", poster.get(0));
		}
		model.addAttribute("StillShot", StillShot);
		model.addAttribute("mTrailer", mTrailer);

	}

	@Override
	public void update(HttpServletRequest request, Map map, MultipartFile poster, MultipartFile[] upload,
			String[] fileKey, String[] fileName) {
		// 영화 수정 처리
		int result = dao.update(map);
		// 정보 수정

		// 포스터 파일 갱신
		map.put("dir", "poster");
		fileUpdate(request, poster, (String) map.get("posterKey"), (String) map.get("posterName"), map);

		// 스틸샷 파일 갱신
		map.put("dir", "stillshot");
		for (int i = 0; i < 3; i++) {
			// 3번 반복
			fileUpdate(request, upload[i], fileKey[i], fileName[i], map);

		}

	}

	@Override
	public int delete(HttpServletRequest request, int mKey) {

		posDelete(request, mKey);
		// 1.포스터 db와 첨부파일 삭제.

		ssAllDelete(request, mKey);
		// 2. 스틸샷 db와 첨부파일 삭제.

		dao.delete(mKey);
		// 3. 삭제 후 영화 db 지우기

		return 0;
	}

	private void posUpload(HttpServletRequest request, int mKey, MultipartFile poster) {
		// 포스터 업로드 메서드
		Map map = new HashMap();
		map.put("mKey", mKey);
		map.put("dir", "poster");
		String newFileDir = fileUpload(request, poster, map);
		// 파일 업로드
		if (newFileDir != null) {

			map.put("newFileDir", newFileDir);
			dao.posInsert(map);
			// 포스터 정보 db저장
		}

	}

	private void posDelete(HttpServletRequest request, int mKey) {
		// 포스터 db와 첨부파일 삭제.

		List<Map> list = dao.posSelect(mKey);
		// db가져오기
		for (Map posDB : list) {
			fileDelete(request, posDB);
			// 파일 삭제
		}

		dao.posDelete(mKey);
		// db삭제
	}

	private void ssUpload(HttpServletRequest request, int mKey, MultipartFile upload) {
		// 스틸샷 업로드 메서드
		Map map = new HashMap();
		map.put("mKey", mKey);
		map.put("dir", "stillshot");

		String newFileDir = fileUpload(request, upload, map);
		if (newFileDir != null) {
			// 파일 업로드
			map.put("newFileDir", newFileDir);
			dao.ssInsert(map);
			// 파일 정보 db저장
		}
	}

	private void ssAllDelete(HttpServletRequest request, int mKey) {
		// 모든 스틸샷 db와 첨부파일 삭제.

		List<Map> list = dao.ssAllSelect(mKey);
		// db 가져오기
		for (Map ssDB : list) {
			fileDelete(request, ssDB);
			// 파일 삭제
			dao.ssDelete((int) ssDB.get("fileKey"));
			// db삭제
		}

	}

	private void ssDelete(HttpServletRequest request, Map map) {
		// 스틸샷 삭제 메서드

		List list = dao.ssSelect(map);
		Map temp = (Map) list.get(0);
		map.put("fileModify", temp.get("fileModify"));

		fileDelete(request, map);
		// 파일 삭제
		dao.ssDelete(Integer.parseInt((String) map.get("fileKey")));
	}

	private String fileUpload(HttpServletRequest request, MultipartFile file, Map map) {
		// 첨부파일 업로드 메서드
		String dir = (String) map.get("dir");
		String uploadDir = request.getRealPath("/");// 서버의 실제 주소
		String fileUploadPathTail = "resources/upload/" + dir + "/";
		// realpath 이후 디렉토리
		String fileUploadPath = uploadDir + fileUploadPathTail;
		// 실제 파일이 위치할 Dir 주소
		String newFiledir = null;

		try {

			if (file.getSize() > 0) {
				// 첨부파일이 있으면
				String fileName = file.getOriginalFilename();// 파일 이름
				String fileNameExt = fileName.substring(fileName.indexOf(".") + 1);// 확장자

				if (check_Img(fileNameExt)) {
					//첨부파일이 이미지가 맞으면

					if (!"".equals(fileName)) {
						File destD = new File(fileUploadPath);
						// 디렉토리 객체

						if (!destD.exists()) {
							destD.mkdirs();
							// 디렉토리가 없으면 디렉토리 생성
						}

						File destination = null;

						if (dir.equals("poster")) {
							// 포스터는 poster_영화키 로 생성
							destination = new File(fileUploadPath + dir + "_" + map.get("mKey"));
							destination.createNewFile();
						} else {
							// 스틸샷은 stillshot_으로 시작하고 확장자로 끝나는 임시파일 생성
							destination = File.createTempFile(dir + "_", "." + fileNameExt, destD);
						}
						file.transferTo(destination);

						String upFileName = destination.getName();

						newFiledir = "/" + fileUploadPathTail + upFileName;

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return newFiledir;
		}
	}

	private void fileDelete(HttpServletRequest request, Map map) {
		// 파일 삭제 메서드
		String uploadDir = request.getRealPath("/");// 서버의 실제 주소
		String FileName = (String) map.get("fileModify");// resources 부터 파일 주소

		File file = new File(uploadDir + FileName);

		file.delete();

	}

	private void fileUpdate(HttpServletRequest request, MultipartFile upload, String fileKey, String fileName,
			Map map) {
		// 첨부파일 업데이트 여부 메서드
		String dir = (String) map.get("dir");
		String mKeystr = (String) map.get("mKey");
		int mKey = Integer.parseInt(mKeystr);

		boolean keyExist = false;
		boolean fileExist = false;
		boolean nameExist = false;
		// fileKey와 fileName은 수정 폼 페이지에서 가져옴

		if (fileKey.length() > 0) {
			keyExist = true;
			// 키가 있으면 true
		}

		if (upload.getSize() > 0) {
			fileExist = true;
			// 첨부 파일이 있으면 true
		}

		// if (!(fileName == null || fileName == "")) {
		if (fileName.length() > 0) {
			nameExist = true;
			// 파일명이 존재하면 true
		}

		if (keyExist && !fileExist) {
			// 키는 있는데 파일은 없다? 업로드 없음.
			if (nameExist) {
				// 파일명은 존재. 업로드 유지.
				return;
			} else {
				// 파일명도 없다. 삭제.
				if (dir.equals("poster")) {
					posDelete(request, mKey);
				} else if (dir.equals("stillshot")) {
					Map ssMap = new HashMap();
					ssMap.put("fileKey", fileKey);

					ssDelete(request, ssMap);
				}
			}
		} else if (keyExist && fileExist) {
			// 키도 있고 파일도 있다. 업로드 파일 갱신.
			// 기존 파일 삭제 후 새 업로드 진행
			if (dir.equals("poster")) {
				posDelete(request, mKey);
				posUpload(request, mKey, upload);

			} else if (dir.equals("stillshot")) {
				Map ssMap = new HashMap();
				ssMap.put("fileKey", fileKey);

				ssDelete(request, ssMap);
				ssUpload(request, mKey, upload);
			}

		} else if (!keyExist && fileExist) {
			// 키는 없는데 파일은 있다. 새 업로드.
			if (dir.equals("poster")) {
				posUpload(request, mKey, upload);

			} else if (dir.equals("stillshot")) {
				ssUpload(request, mKey, upload);
			}
		} else if (!keyExist && !fileExist) {
			// 키도 없고 파일도 없다. 업로드 없음.
			return;
		}
	}

	private boolean check_Img(String fileNameExt) {
		// 파일의 확장자가 이미지가 맞는지 체크
		String Ext[] = { "jpg", "gif", "png", "jpeg", "jpe", "jfif", "tif", "tiff" };

		for (String str : Ext) {
			if (str.equalsIgnoreCase(fileNameExt)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void search(Model model, Map map) {

		int curPage = 1;
		if (map.get("curPage") != null) {
			curPage = Integer.parseInt((String) map.get("curPage"));
		}

		String searchWord = (String) map.get("searchWord");
		if (searchWord == null) {
			searchWord = "";
		}

		RestTemplate restTemplate = new RestTemplate();

		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?";
		url += "key=e20b74e72adebd9deb7baed8f2de0b3f";// 발급받은 개인 Key
		// url += "key=430156241533f1d058c603178cc3ca0e";//영화진흥위원회 예시용 키
		url += "&movieNm=" + searchWord;
		url += "&curPage=" + curPage;
		// 영화진흥위원회의 정보를 받을 get방식 url

		URI uri = URI.create(url);
		String responseString = restTemplate.getForObject(uri, String.class);
		// url의 결과를 String으로 받음.
		// json을 받아옴.

		Gson gson = new Gson();
		Map responseMap = gson.fromJson(responseString, HashMap.class);
		Map resultMap = (Map) responseMap.get("movieListResult");
		// json object를 맵으로

		int totCnt = ((Double) resultMap.get("totCnt")).intValue();
		List movieList = (List) resultMap.get("movieList");

		int startPage = 1;
		int maxPage = totCnt / 10;
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

		model.addAttribute("curPage", curPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("searchWord", searchWord);

		model.addAttribute("totCnt", totCnt);
		model.addAttribute("movieList", movieList);

	}
}
