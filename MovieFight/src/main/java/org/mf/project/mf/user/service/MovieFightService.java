package org.mf.project.mf.user.service;

import java.util.Map;

import org.springframework.ui.Model;

public interface MovieFightService {

	void mlist(Model model, Map map);

	void content(Model model, Map map);

	String setVote(Map map);

	String getVote(Map map);

	String insertRep(Map map);

	String reGood(Map map);

	void reply(Model model, Map map);

	String repRefresh(Map map);

}
