package com.cidic.sdx.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.model.CustomerModel;

@Repository
@Component
@Qualifier(value = "customerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {

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
	public void insertCustomer(CustomerModel model) {
		
	}

	@Override
	public List<CustomerModel> getCustomerByPage(int limit, int offset) {
		
		return null;
	}

}
