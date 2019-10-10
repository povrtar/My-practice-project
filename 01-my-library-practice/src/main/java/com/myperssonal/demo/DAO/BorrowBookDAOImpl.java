package com.myperssonal.demo.DAO;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myperssonal.demo.entity.BorrowBook;
import com.myperssonal.demo.entity.Customer;
@Repository
public class BorrowBookDAOImpl implements BorrowBookDAO {
	
private EntityManager entityManager;
@Autowired
public BorrowBookDAOImpl(EntityManager theEntityManager) {
	entityManager=theEntityManager;
}
	@Override
	public void saveBorrowBook(BorrowBook bb) {
		Session session=entityManager.unwrap(Session.class);
session.save(bb);
	}

	@Override
	public BorrowBook getBorrowBook(int theId) {
		Session session=entityManager.unwrap(Session.class);
		BorrowBook bb=session.get(BorrowBook.class,theId);
		return bb;
	}

	@Override
	public String deleteBorrowBook(BorrowBook bb) {
		Session session=entityManager.unwrap(Session.class);

		Query theQuery = 
				session.createQuery("delete from BorrowBook where id=:bookId and customerId = :customerId");
		theQuery.setParameter("bookId",bb.getBookId());
		theQuery.setParameter("customerId",bb.getCustomerId());
		theQuery.executeUpdate();
		long date= ZonedDateTime.now().toInstant().toEpochMilli();
		
		int holdingtime=(int) (((int)date/1000/60/60/24)-bb.getDate());
		return "Book Id: "+bb.getBookId()+" borrowed from Custome Id; "+bb.getCustomerId()+" is returned after "+holdingtime+" days!";
	}
	@Override
	public List<BorrowBook> getBorrowedBooks(int id) {
		// get the current hibernate session
		Session currentSession =entityManager.unwrap(Session.class);
				
		// create a query  ... sort by last name
		Query<BorrowBook> theQuery = 
				currentSession.createQuery("from BorrowBook where id=:bookId",
											BorrowBook.class);
		theQuery.setParameter("bookId",id);
		// execute query and get result list
		List<BorrowBook> borrowedBooks = theQuery.getResultList();
				
		// return the results		
		return borrowedBooks;
	
	}

}
