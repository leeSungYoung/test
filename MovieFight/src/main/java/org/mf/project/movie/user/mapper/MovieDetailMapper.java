package org.mf.project.movie.user.mapper;

import java.util.List;
import java.util.Map;

public interface MovieDetailMapper {

	public List movieDetail(int mKey);

	public int setReply(Map map);

	public List getReply(Map map);

	public List getAllReply(Map map);

	public int upReply(Map map);

	public int setGood(Map map);

	public int getGood(Map map);

	public int setStar(Map map);

	public List getStar(Map map);

	public int upStar(Map map);
	
	public int mStar(Map map);

	public List seletPoster(Map map);

	public List seletStillShot(Map map);

	public int setGoodName(Map map);

	public List getGoodName(Map map);

	public int setZzim(Map map);

	public List getZzim(Map map);

	public int delZzim(int zKey);

	public int mZzim(Map map);
	
	public List moreCallIn(Map map);
	
	public String getAvgStar(Map map);
	
	public List getMovieList(Map map);
	
	public int getTotalMoveCnt(Map map);
	
	public int checkGood(Map map);
}
