package com.myperssonal.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myperssonal.demo.entity.Book;

@Repository
public class BookDaoImpl implements BookDAO {
    @Autowired
    private EntityManager theEntityManager;

    @Override
    public void saveBook(Book theBook) {
        // TODO Auto-generated method stub
        // get current hibernate session
        Session currentSession = theEntityManager.unwrap(Session.class);

        // save/upate the book ... finally LOL
        currentSession.saveOrUpdate(theBook);
    }

    @Override
    public Book getBook(int theId) {
        Session currentSession = theEntityManager.unwrap(Session.class);
        Book theBook = currentSession.get(Book.class, theId);
        return theBook;
    }

    @Override
    public void deleteBook(int theId) {
        Session currentSession = theEntityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        Session currentSession = theEntityManager.unwrap(Session.class);
        Query<Book> theQuery = currentSession.createQuery("from Book order by autorLastName", Book.class);
        List<Book> books = theQuery.getResultList();
        List<Book> booksWithTitle = new ArrayList<>();
        for (Book book : books) {
            if (title.equals(book.getTitle())) {
                booksWithTitle.add(book);
            }
        }
        return booksWithTitle;
    }

    @Override
    public List<Book> getBooksByAutor(String lastName) {
        Session currentSession = theEntityManager.unwrap(Session.class);
        Query<Book> theQuery = currentSession.createQuery("from Book order by autorLastName", Book.class);
        List<Book> books = theQuery.getResultList();
        List<Book> booksOfAutor = new ArrayList<>();
        for (Book book : books) {
            if ((lastName.equals(book.getAutorLastName()))) {
                booksOfAutor.add(book);
            }
        }
        return booksOfAutor;
    }

    @Override
    public List<Book> getBooks() {
        Session currentSession = theEntityManager.unwrap(Session.class);
        Query<Book> theQuery = currentSession.createQuery("from Book order", Book.class);
        List<Book> books = theQuery.getResultList();
        return books;
    }

}
