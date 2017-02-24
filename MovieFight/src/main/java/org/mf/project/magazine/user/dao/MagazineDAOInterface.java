package org.mf.project.magazine.user.dao;

import java.util.List;
import java.util.Map;

public interface MagazineDAOInterface {
	public List magazineList(Map map);

	public List magazineContent(Map map);

	public int updateMagHit(Map map) throws Exception;

	public String getRow();

	public List magazineListPage(int page);
	
	public List magazineListHitPage(int page);
}
