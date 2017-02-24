package org.mf.project.magazine.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public interface MagazineServiceInterface {
	public List magazineList(Map map);

	public List magazineContent(Map map) throws Exception;
	
	public String getRow();

	public List magazineListPage(int page);

	public List magazineListHitPage(int page);
}
