package org.mf.project.movie.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mf.project.movie.user.mapper.MovieDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDtailDaoImpl implements MovieDtailDao{
@Autowired

private SqlSession sqlSession;

@Override
public List movieDetail(int mKey) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.movieDetail(mKey);
}

@Override
public int setReply(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.setReply(map);
}

@Override
public List getReply(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper = sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getReply(map);
}

@Override
public int upReply(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.upReply(map);
}

@Override
public List getAllReply(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getAllReply(map);
}



@Override
public int setGood(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.setGood(map);
}

@Override
public int getGood(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getGood(map);
}

@Override
public int setStar(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.setStar(map);
}

@Override
public List getStar(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getStar(map);
}

@Override
public int upStar(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.upStar(map);
}

@Override
public List seletPoster(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.seletPoster(map);
}

@Override
public List seletStillShot(Map map) {
	// TODO Auto-generated method stub
MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.seletStillShot(map);
}

@Override
public int setGoodName(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.setGoodName(map);
}

@Override
public List getGoodName(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getGoodName(map);
}

@Override
public int setZzim(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.setZzim(map);
}

@Override
public List getZzim(Map map) {
	// TODO Auto-generated method stub
	
	MovieDetailMapper mapper = sqlSession.getMapper(MovieDetailMapper.class);
	
	return mapper.getZzim(map);
}

@Override
public int delZzim(int zKey) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper =sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.delZzim(zKey);
}

@Override
public List moreCallIn(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.moreCallIn(map);
}

@Override
public int mStar(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	
	return mapper.mStar(map);
}

@Override
public int mZzim(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	
	return mapper.mZzim(map);
}

@Override
public String getAvgStar(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	
	return mapper.getAvgStar(map);
}

@Override
public List getMovieList(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper=sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getMovieList(map);
}

@Override
public int getTotalMoveCnt(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.getTotalMoveCnt(map);
}

@Override
public int checkGood(Map map) {
	// TODO Auto-generated method stub
	MovieDetailMapper mapper= sqlSession.getMapper(MovieDetailMapper.class);
	return mapper.checkGood(map);
}


	
	



}
