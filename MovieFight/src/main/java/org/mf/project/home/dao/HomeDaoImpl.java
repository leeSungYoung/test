package org.mf.project.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.home.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDaoImpl implements HomeDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List mf_list() {
		HomeMapper mapper = session.getMapper(HomeMapper.class);
		return mapper.mf_list();
	}
}
