package org.mf.project.home.service;

import java.util.List;

import org.mf.project.home.dao.HomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Component
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeDao dao;
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void index(Model model) {
		List mf_list = dao.mf_list();
		
		model.addAttribute("mf_list",mf_list);
	}
}
