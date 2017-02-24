package org.mf.project.movie.user.controller;

import java.util.Map;

import org.mf.project.movie.user.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MovieController {
	
	@Autowired
	MovieServiceInterface movieService;
	
	// boxoffice.
	@RequestMapping(value = "/movie/boxoffice", method = RequestMethod.GET)
	public String boxoffice(Model model) {
		
		movieService.boxOffice(model);
		
		return "movie/running3";
	}
	
}
