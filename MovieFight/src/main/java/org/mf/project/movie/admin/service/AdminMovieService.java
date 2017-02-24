package org.mf.project.movie.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface AdminMovieService {

	int insert(HttpServletRequest request, Map map, MultipartFile poster,MultipartFile[] upload);

	void search(Model model, Map map);

	void update(Model model, int mKey);

	int delete(HttpServletRequest request, int mKey);

	void update(HttpServletRequest request, Map map, MultipartFile poster, MultipartFile[] upload, String[] fileKey, String[] fileName);

}
