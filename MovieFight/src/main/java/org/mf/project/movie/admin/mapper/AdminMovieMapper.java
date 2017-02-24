package org.mf.project.movie.admin.mapper;

import java.util.List;
import java.util.Map;

public interface AdminMovieMapper {

	int insert(Map map);

	int update(Map map);

	int delete(int mKey);

	int posInsert(Map map);

	int ssInsert(Map map);

	List posSelect(int mKey);

	List<Map> ssAllSelect(int mKey);

	List ssSelect(Map map);

	int posDelete(int mKey);

	int ssDelete(int fileKey);

}
