package org.mf.project.magazine.admin.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mf.project.magazine.admin.dao.AdminMagazineDao;
import org.mf.project.magazine.user.dao.MagazineDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Component
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class AdminMagazineServiceImpl implements AdminMagazineService {

	@Autowired
	private AdminMagazineDao dao;

	@Autowired
	private MagazineDAOInterface magazineDAO;

	@Override
	public int insert(Map map) {

		// 매거진 내용중 제일 첫 이미지 가져오기
		String magContent = (String) map.get("magContent");
		int imgStart = magContent.indexOf("<img");// <img 시작 위치
		if (imgStart >= 0) {// <img 태그가 없으면 -1 반환
			int srcStart = magContent.indexOf("src=", imgStart);
			int firstComm = magContent.indexOf('"', srcStart);
			int end = magContent.indexOf('"', firstComm + 1);
			String magImg = magContent.substring(firstComm + 1, end);
			map.put("magImg", magImg);
		}

		return dao.insert(map);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void update(Model model, int magKey) {

		Map map = new HashMap();
		map.put("magkey", magKey);

		List list = magazineDAO.magazineContent(map);
		Map mag = (Map) list.get(0);
		mag.put("magKey", magKey);
		model.addAttribute("mag", mag);

	}

	@Override
	public int update(Map map) {
		String magContent = (String) map.get("magContent");
		int imgStart = magContent.indexOf("<img");// <img 시작 위치
		if (imgStart >= 0) {// <img 태그가 없으면 -1 반환
			int srcStart = magContent.indexOf("src=", imgStart);
			int firstComm = magContent.indexOf('"', srcStart);
			int end = magContent.indexOf('"', firstComm + 1);
			String magImg = magContent.substring(firstComm + 1, end);
			map.put("magImg", magImg);
		}
		return dao.update(map);
	}

	@Override
	public int delete(int magKey) {
		return dao.delete(magKey);
	}

	@Override
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		String uploadDir = request.getRealPath("/");// 서버의 실제 주소
		String contextPath = request.getSession().getServletContext().getContextPath();
		String fileUploadPathTail = "resources/upload/magazine/";
		// realpath 이후 디렉토리
		String fileUploadPath = uploadDir + "/" + fileUploadPathTail;
		// 실제 파일이 위치할 Dir 주소

		PrintWriter printWriter = null;

		try {

			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();

			if (upload != null) {

				String fileName = upload.getOriginalFilename();// 파일 이름
				String fileNameExt = fileName.substring(fileName.indexOf(".") + 1);// 확장자

				if (check_Img(fileNameExt)) {

					if (!"".equals(fileName)) {
						File destD = new File(fileUploadPath);
						// 디렉토리 객체

						if (!destD.exists()) {
							destD.mkdirs();
							// 디렉토리가 없으면 디렉토리 생성
						}

						File destination = File.createTempFile("ckeditor_", "." + fileNameExt, destD);
						// destD위치에 'ckeditor_' 로 시작하고 '.확장자'로 끝나는 임시 파일 생성
						upload.transferTo(destination);

						String upFileName = destination.getName();
						// 업로드 된 파일의 이름

						printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
								+ callback + ",'" + "/" + contextPath + fileUploadPathTail + upFileName
								+ "','이미지를 업로드 하였습니다.'" + ")</script>");
						// 업로드 후 화면에 이미지 주소를 리턴

					}

				} else {
					printWriter.println("<script type='text/javascript'>alert('이미지를 선택하여 주세요.');</script>");
					// 이미지가 아닐때 경고창
				}
				printWriter.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
}
