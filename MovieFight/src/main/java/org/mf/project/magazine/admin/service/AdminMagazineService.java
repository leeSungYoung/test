package org.mf.project.magazine.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface AdminMagazineService {

	void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload);

	int insert(Map map);

	void update(Model model, int magKey);

	int update(Map map);

	int delete(int magKey);

}
