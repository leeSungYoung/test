package org.mf.project.member.user.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberMapper {

	public int insertMember(Map map);

	public int deleteMember(Map map);

	public int updatePW(Map map);

	public int updateMember(Map map);

	public String selectStar(Map map);

	public List getAllMember(Map map);

	public Map checkIdDup(Map map);

	public List<HashMap> selectPW(Map map);

	public List getMemKey_star(Map map);
	
	public List selectZzimMovie(Map map);

}
