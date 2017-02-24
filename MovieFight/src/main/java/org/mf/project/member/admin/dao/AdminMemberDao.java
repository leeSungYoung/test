package org.mf.project.member.admin.dao;

import java.util.List;
import java.util.Map;

public interface AdminMemberDao {

	public List<Map> list(Map map);

	public int count();
}
