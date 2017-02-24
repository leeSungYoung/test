package org.mf.project.movie.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.movie.admin.mapper.AdminMovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMovieDaoImpl implements AdminMovieDao {

	@Autowired
	private SqlSession session;

	@Override
	public int insert(Map map) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);

		return mapper.insert(map);
	}
	
	@Override
	public int update(Map map) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.update(map);
	}
	
	@Override
	public int delete(int mKey) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.delete(mKey);
	}

	@Override
	public int posInsert(Map map) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);

		return mapper.posInsert(map);
	}
	
	@Override
	public int posDelete(int mKey) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.posDelete(mKey);
	}
	
	@Override
	public List posSelect(int mKey) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.posSelect(mKey);
	}

	@Override
	public int ssInsert(Map map) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);

		return mapper.ssInsert(map);
	}
	
	@Override
	public int ssDelete(int fileKey) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.ssDelete(fileKey);
	}
	
	@Override
	public List<Map> ssAllSelect(int mKey) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.ssAllSelect(mKey);
	}
	@Override
	public List ssSelect(Map map) {
		AdminMovieMapper mapper = session.getMapper(AdminMovieMapper.class);
		return mapper.ssSelect(map);
	}
}
