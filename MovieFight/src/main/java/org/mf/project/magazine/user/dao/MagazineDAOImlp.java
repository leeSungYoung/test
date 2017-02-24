package org.mf.project.magazine.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.magazine.user.mapper.MagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MagazineDAOImlp implements MagazineDAOInterface{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List magazineList(Map map) {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
		return mapper.magazineList(map);
	}

	@Override
	public List magazineContent(Map map) {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
		return mapper.magazineContent(map);
	}

	@Override
	public int updateMagHit(Map map) throws Exception {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
		return mapper.updateMagHit(map);
		
	}

	@Override
	public String getRow() {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
		return mapper.getRow();
	}

	@Override
	public List magazineListPage(int page) {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
//		int page = Integer.parseInt(seq);
		return mapper.magazineListPage(page);
	}
	
	@Override
	public List magazineListHitPage(int page) {
		MagazineMapper mapper = sqlSession.getMapper(MagazineMapper.class);
		return mapper.magazineListHitPage(page);
	}


}
