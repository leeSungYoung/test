package org.mf.project.movie.user.service;

import org.mf.project.storage.BoxOffice;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class MovieServiceImpl implements MovieServiceInterface {

	@Override
	public void boxOffice(Model model) {
		
		model.addAttribute("runningMovieList", BoxOffice.getRefreshList());
		
	}	

}

	

	

