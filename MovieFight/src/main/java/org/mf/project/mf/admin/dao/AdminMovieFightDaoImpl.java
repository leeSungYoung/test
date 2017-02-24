package org.mf.project.mf.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.mf.admin.mapper.AdminMovieFightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMovieFightDaoImpl implements AdminMovieFightDao {

	@Autowired
	private SqlSession session;

	@Override
	public int insert(Map map) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		
		return mapper.insert(map);
	}
	
	@Override
	public int update(Map map) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		return mapper.update(map);
	}
	
	@Override
	public int deleteMF(int mfKey) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		return mapper.deleteMF(mfKey);
	}
	
	@Override
	public int deleteVote(int mfKey) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		return mapper.deleteVote(mfKey);
	}
	
	@Override
	public int deleteReply(int mfKey) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		return mapper.deleteReply(mfKey);
	}
	
	@Override
	public List search(Map data) {

		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);

		return mapper.search(data);
	}
	@Override
	public int count(Map map) {
		AdminMovieFightMapper mapper = session.getMapper(AdminMovieFightMapper.class);
		return mapper.count(map);
	}
}
