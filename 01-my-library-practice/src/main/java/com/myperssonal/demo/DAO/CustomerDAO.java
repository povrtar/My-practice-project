package com.myperssonal.demo.DAO;

import java.util.List;

import com.myperssonal.demo.entity.Customer;



public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
