package org.mf.project.mf.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.mf.user.mapper.MovieFightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieFightDaoImpl implements MovieFightDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List mlist(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		
		return mapper.mlist(map);
	}
	
	@Override
	public List content(int mfKey) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.content(mfKey);
	}
	
	@Override
	public List haveVote(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.haveVote(map);
	}
	
	@Override
	public int setVote(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.setVote(map);
	}
	
	@Override
	public List getVote(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.getVote(map);
	}
	@Override
	public int insertRep(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.insertRep(map);
	}
	
	@Override
	public int updateRep(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.updateRep(map);
	}
	
	@Override
	public List haveRep(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.haveRep(map);
	}
	
	@Override
	public String userRep(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.userRep(map);
	}
	@Override
	public List repList(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.repList(map);
	}
	
	@Override
	public List selfCheck(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.selfCheck(map);
	}
	
	@Override
	public List haveGood(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.haveGood(map);
	}
	
	@Override
	public int reGood(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.reGood(map);
	}
	@Override
	public int repGoodup(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.repGoodup(map);
	}
	
	@Override
	public List reply(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.reply(map);
	}
	
	@Override
	public int count(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.count(map);
	}
	@Override
	public int replyCount(Map map) {
		MovieFightMapper mapper = session.getMapper(MovieFightMapper.class);
		return mapper.replyCount(map);
	}
}
