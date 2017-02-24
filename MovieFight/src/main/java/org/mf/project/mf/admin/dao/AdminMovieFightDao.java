package org.mf.project.mf.admin.dao;

import java.util.List;
import java.util.Map;

public interface AdminMovieFightDao {

	List search(Map data);

	int insert(Map map);

	int update(Map map);

	int count(Map map);

	int deleteReply(int mfKey);

	int deleteVote(int mfKey);

	int deleteMF(int mfKey);

}
