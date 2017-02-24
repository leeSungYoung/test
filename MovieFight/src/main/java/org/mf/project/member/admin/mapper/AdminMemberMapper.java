package org.mf.project.member.admin.mapper;

import java.util.List;
import java.util.Map;

public interface AdminMemberMapper {

	public List<Map> list(Map map);

	public int count();
}
