package com.cidic.sdx.dao;

import java.util.List;

import com.cidic.sdx.model.CustomerModel;

public interface CustomerDao {
	
	public void insertCustomer(CustomerModel model);
	
	public List<CustomerModel> getCustomerByPage(int limit, int offset);
}
