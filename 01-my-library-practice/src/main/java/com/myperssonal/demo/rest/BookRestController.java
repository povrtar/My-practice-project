package com.myperssonal.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.Book;
import com.myperssonal.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> getBooks() {

        List<Book> books = bookService.getBooks();
        return books;
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        int id = Integer.valueOf(bookId);
        Book theBook = bookService.getBook(id);
        if (theBook == null) {
            throw new EntityNotFoundException("Book not founded for id: " + bookId);
        }
        return theBook;
    }

    @GetMapping("/bytitle/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        List<Book> theBooks = bookService.getBooksByTitle(title);
        if (theBooks.size() == 0) {
            throw new EntityNotFoundException("Book with title " + title + "  not founded !!!");
        }
        return theBooks;
    }

    @GetMapping("/byautor/{lastName}")
    public List<Book> getBooksByAutor(@PathVariable String lastName) {
        List<Book> theBooks = bookService.getBooksByAutor(lastName);
        if (theBooks.size() == 0) {
            throw new EntityNotFoundException("Book writen by " + lastName + "  not founded !!!");
        }
        return theBooks;
    }
}