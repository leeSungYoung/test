package org.mf.project.magazine.admin.dao;

import java.util.Map;

public interface AdminMagazineDao {

	int insert(Map map);

	int update(Map map);

	int delete(int magKey);

}
