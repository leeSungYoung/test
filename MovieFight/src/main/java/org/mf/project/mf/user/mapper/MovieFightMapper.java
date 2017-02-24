package org.mf.project.mf.user.mapper;

import java.util.List;
import java.util.Map;

public interface MovieFightMapper {

	List mlist(Map map);

	List content(int mfKey);

	List haveVote(Map map);

	int setVote(Map map);

	List getVote(Map map);

	int insertRep(Map map);

	int updateRep(Map map);

	List haveRep(Map map);

	String userRep(Map map);

	List repList(Map map);

	List haveGood(Map map);

	int reGood(Map map);

	int repGoodup(Map map);

	List reply(Map map);

	int count(Map map);

	int replyCount(Map map);

	List selfCheck(Map map);

}
