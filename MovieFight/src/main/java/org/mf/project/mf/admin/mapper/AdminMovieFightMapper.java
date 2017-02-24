package org.mf.project.mf.admin.mapper;

import java.util.List;
import java.util.Map;

public interface AdminMovieFightMapper {

	List search(Map data);

	int insert(Map map);

	int update(Map map);

	int count(Map map);

	int deleteMF(int mfKey);

	int deleteVote(int mfKey);

	int deleteReply(int mfKey);

}
