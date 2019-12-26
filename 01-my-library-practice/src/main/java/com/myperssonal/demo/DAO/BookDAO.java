package com.myperssonal.demo.DAO;

import java.util.List;

import com.myperssonal.demo.entity.Book;

public interface BookDAO {

    public void saveBook(Book theBook);

    public Book getBook(int theId);

    public List<Book> getBookByTitle(String title);

    public List<Book> getBooksByAutor(String lastName);

    public void deleteBook(int theId);

    public List<Book> getBooks();
}
