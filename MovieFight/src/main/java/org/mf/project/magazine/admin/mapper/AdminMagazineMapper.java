package org.mf.project.magazine.admin.mapper;

import java.util.Map;

public interface AdminMagazineMapper {

	int insert(Map map);

	int update(Map map);

	int delete(int magKey);

}
