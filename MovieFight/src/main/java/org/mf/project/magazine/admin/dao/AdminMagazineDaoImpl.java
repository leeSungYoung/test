package org.mf.project.magazine.admin.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.magazine.admin.mapper.AdminMagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMagazineDaoImpl implements AdminMagazineDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public int insert(Map map) {
		AdminMagazineMapper mapper = session.getMapper(AdminMagazineMapper.class);
		return mapper.insert(map);
	}
	
	@Override
	public int update(Map map) {
		AdminMagazineMapper mapper = session.getMapper(AdminMagazineMapper.class);
		return mapper.update(map);
	}
	
	@Override
	public int delete(int magKey) {
		AdminMagazineMapper mapper = session.getMapper(AdminMagazineMapper.class);
		return mapper.delete(magKey);
	}
	
}
