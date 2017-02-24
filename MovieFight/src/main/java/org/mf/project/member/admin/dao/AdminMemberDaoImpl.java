package org.mf.project.member.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.member.admin.mapper.AdminMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMemberDaoImpl implements AdminMemberDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map> list(Map map) {
		AdminMemberMapper mapper = sqlSession.getMapper(AdminMemberMapper.class);
		return mapper.list(map);
	}

	@Override
	public int count() {
		AdminMemberMapper mapper = sqlSession.getMapper(AdminMemberMapper.class);
		return mapper.count();
	}
}
