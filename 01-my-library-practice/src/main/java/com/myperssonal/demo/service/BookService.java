package com.myperssonal.demo.service;

import java.util.List;

import com.myperssonal.demo.entity.Book;

public interface BookService {

    public void saveBook(Book theBook);

    public Book getBook(int theId);

    public void deleteBook(int theId);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getBooksByAutor(String lastName);
}
