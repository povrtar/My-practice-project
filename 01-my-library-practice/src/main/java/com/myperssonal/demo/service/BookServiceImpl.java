package com.myperssonal.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myperssonal.demo.DAO.BookDAO;
import com.myperssonal.demo.entity.Book;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDAO bookDao;

	@Override
	@Transactional
	public void saveBook(Book theBook) {
		bookDao.saveBook(theBook);
	}

	@Override
	@Transactional
	public Book getBook(int theId) {
		// TODO Auto-generated method stub
		return bookDao.getBook(theId);
	}

	@Override
	@Transactional
	public void deleteBook(int theId) {
		// TODO Auto-generated method stub
		bookDao.deleteBook(theId);
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		return bookDao.getBookByTitle(title);
	}

	@Override
	public List<Book> getBooksByAutor(String lastName) {
		return bookDao.getBooksByAutor(lastName);
	}

}
