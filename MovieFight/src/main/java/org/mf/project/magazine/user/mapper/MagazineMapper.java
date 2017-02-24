package org.mf.project.magazine.user.mapper;

import java.util.List;
import java.util.Map;

public interface MagazineMapper {
	public List checkGood(Map map);

	public List magazineList(Map map);

	public List magazineContent(Map map);
	
	public int updateMagHit(Map map);

	public String getRow();

	public List magazineListPage(int page);
	
	public List magazineListHitPage(int page);
}
