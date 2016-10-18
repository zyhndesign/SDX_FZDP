package com.cidic.sdx.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.model.MatchModel;

@Repository
@Component
@Qualifier(value = "matchDaoImpl")
public class MatchDaoImpl implements MacthDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertMatch(MatchModel matchDao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MatchModel> getMatchByPage(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchModel getMatchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
