package org.mf.project.movie.admin.dao;

import java.util.List;
import java.util.Map;

public interface AdminMovieDao {

	int insert(Map map);

	int posInsert(Map map);

	int ssInsert(Map map);

	int delete(int mKey);

	List<Map> posSelect(int mKey);

	List<Map> ssAllSelect(int mKey);

	List ssSelect(Map map);

	int posDelete(int mKey);

	int ssDelete(int mKey);

	int update(Map map);

}
