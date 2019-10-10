package com.myperssonal.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myperssonal.demo.DAO.BookDAO;
import com.myperssonal.demo.DAO.BorrowBookDAO;
import com.myperssonal.demo.DAO.CustomerDAO;
import com.myperssonal.demo.entity.Book;
import com.myperssonal.demo.entity.BorrowBook;
import com.myperssonal.demo.entity.Customer;
@Service
public class BorrowServiceImpl implements BorrowService {
	private List<BorrowBook> bbList;
private BookDAO theBookDAO;
private CustomerDAO theCustomerDAO;
private BorrowBookDAO bb;
public BorrowServiceImpl(BookDAO bookDao,CustomerDAO customerDao,BorrowBookDAO theBB) {
	theBookDAO=bookDao;
	theCustomerDAO=customerDao;
	bb=theBB;
}
	@Override
	@Transactional
	public void borrowBook(BorrowBook theBorrowBook) {
	 int theBookId=theBorrowBook.getBookId();
	 int theCustomerId=theBorrowBook.getCustomerId();
	Book theBook=theBookDAO.getBook(theBookId);
Customer theCustomer=theCustomerDAO.getCustomer(theCustomerId);
if((theBook.getUnitStrength()==0)||(theCustomer.getForReverse()==3)) {
	throw new RuntimeException("This is imposibile transaction");
}else {
	theBook.setUnitStrength(theBook.getUnitStrength()-1);
	theCustomer.setForReverse(theCustomer.getForReverse()+1);
	theBookDAO.saveBook(theBook);
	theCustomerDAO.saveCustomer(theCustomer);
	bb.saveBorrowBook(theBorrowBook);
}
	}

	@Override
	@Transactional
	public void reverseBook(BorrowBook theBorrowBook) {
	boolean find=false;
	
		 int theBookId=theBorrowBook.getBookId();
		 int theCustomerId=theBorrowBook.getCustomerId();
		
		bbList=bb.getBorrowedBooks(theBookId);
		System.out.println(bbList);
		for(BorrowBook borBook:bbList) {
			System.out.println(borBook.getBookId()+"="+theBorrowBook.getBookId()+" "+borBook.getCustomerId()+"="+theBorrowBook.getCustomerId());
		if(borBook.getBookId()==theBorrowBook.getBookId()&&borBook.getCustomerId()==theBorrowBook.getCustomerId())	{find=true;
			Book theBook=theBookDAO.getBook(theBookId);
	Customer theCustomer=theCustomerDAO.getCustomer(theCustomerId);
		theBook.setUnitStrength(theBook.getUnitStrength()+1);
		theCustomer.setForReverse(theCustomer.getForReverse()-1);
		theBookDAO.saveBook(theBook);
		theCustomerDAO.saveCustomer(theCustomer);
bb.deleteBorrowBook(theBorrowBook);}
		
	}
		if(!find) {throw new RuntimeException("Incorect reverse data");}
	}
}