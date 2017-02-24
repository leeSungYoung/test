package org.mf.project.member.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.member.user.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int joinStep2(Map map) {
		// 회원가입 처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.insertMember(map);

	}

	@Override
	public int delete2(Map map) {
		// 탈퇴처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.deleteMember(map);
	}

	@Override
	public int updatePW2(Map map) {
		// 비밀번호 수정 처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.updatePW(map);
	}

	@Override
	public int update2(Map map) {
		// 회원 정보 수정 처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		return mapper.updateMember(map);
	}

	
	@Override
	public List getAllMember(Map map) {
		// 로그인 처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.getAllMember(map);
	}

	@Override
	public Map checkIdDup(Map map) {
		// 아이디 중복 검사
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.checkIdDup(map);
	}

	@Override
	public List<HashMap> findPW2(Map map) {
		// 비밀번호 찾기 처리
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.selectPW(map);
	}

	
	@Override
	public List getMemKey_star(Map map) {
		//영화 평가 리스트
		MemberMapper mapper= sqlSession.getMapper(MemberMapper.class);
		
		return mapper.getMemKey_star(map);
	}

	@Override
	public List zzimMovie(Map map) {
		//영화 찜 리스트
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		return mapper.selectZzimMovie(map);
	}

}
