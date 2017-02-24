package org.mf.project.member.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberDao {
		
	public int joinStep2(Map map);

	public int delete2(Map map);

	public int updatePW2(Map map);

	public int update2(Map map);

	public List getAllMember(Map map);

	public Map checkIdDup(Map map);

	public List<HashMap> findPW2(Map map);

	public List zzimMovie(Map map);
	
	public List getMemKey_star(Map map);

}
