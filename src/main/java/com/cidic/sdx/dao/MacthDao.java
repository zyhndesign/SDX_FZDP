package com.cidic.sdx.dao;

import java.util.List;

import com.cidic.sdx.model.MatchModel;

public interface MacthDao {
	
	public void insertMatch(MatchModel matchDao);
	
	public List<MatchModel> getMatchByPage(int limit, int offset);
	
	public MatchModel getMatchById(int id);
}
