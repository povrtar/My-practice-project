package com.myperssonal.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myperssonal.demo.entity.Customer;



@Repository
public class CustomerDAOImpl implements CustomerDAO {
	private EntityManager entityManager;
	@Autowired
	public CustomerDAOImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
			
	@Override
	public List<Customer> getCustomers() {
		Session currentSession =entityManager.unwrap(Session.class);
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",Customer.class);
		List<Customer> customers = theQuery.getResultList();	
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Customer theCustomer = currentSession.get(Customer.class, theId);		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

}











