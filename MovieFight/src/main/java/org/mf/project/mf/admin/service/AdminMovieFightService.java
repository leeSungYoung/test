package org.mf.project.mf.admin.service;

import java.util.Map;

import org.springframework.ui.Model;

public interface AdminMovieFightService {

	int search(Model model, Map map);

	int insert(Map map);

	void content(Model model, String mfKey);

	int update(Map map);

	void delete(String mfKey);

}
