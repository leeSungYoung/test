package org.mf.project.magazine.user.service;

import java.util.List;
import java.util.Map;

import org.mf.project.magazine.user.dao.MagazineDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class MagazineServiceImpl implements MagazineServiceInterface {

	@Autowired
	MagazineDAOInterface magazineDAO;

	@Override
	public List magazineList(Map map) {
		return magazineDAO.magazineList(map);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	// magazineContent의 데이터를 받아오기 전에 magHit(조회수)를 +1 해준다.
	public List magazineContent(Map map) throws Exception {
		magazineDAO.updateMagHit(map);
		return magazineDAO.magazineContent(map);
	}

	@Override
	public String getRow() {
		return magazineDAO.getRow();
	}

	@Override
	public List magazineListPage(int page) {
		// TODO Auto-generated method stub
		return magazineDAO.magazineListPage(page);
	}

	@Override
	public List magazineListHitPage(int page) {
		// TODO Auto-generated method stub
		return magazineDAO.magazineListHitPage(page);
	}
	
	


}
