package com.cidic.sdx.dao;

import com.cidic.sdx.model.HPListModel;
import com.cidic.sdx.model.HPModel;

public interface MacthBacketDao {
	
	public void addMatchBacket(int userId, HPModel hPModel);
	
	public void deleteMatchBacketByUser(int userId, String hpbh);
	
	public HPListModel getMatchBacketByUser(int userId,int page, int limit);
}
