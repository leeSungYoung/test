package org.mf.project.movie.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public interface MovieDetailService {
	public List movieDetail(int mKey);

	public int setReply(Map map);

	public String getReply(Map map);

	public int upReply(Map map);

	public List getAllReply(Map map);

	public String setGood(Map map);

	public int getGood(Map map);

	public String setStar(Map map);

	public List getStar(Map map);
	
	public int mStar(Map map);

	public List seletStillShot(Map map);

	public int zzim(Map map);
	
	
	void movieInfo(Model model, Map map);
	
	public List moreCallIn(Map map);
	
	public int mZzim(Map map);
	
	public String getAvgStar(Map map);
	public void getMovieList(Model model,Map map);
	public int pageing(Map map);
}
