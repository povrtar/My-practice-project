package com.myperssonal.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.Book;
import com.myperssonal.demo.service.BookService;

@RestController
@RequestMapping("/api")
public class BookRestController {

	BookService bookService;

	@Autowired
	public BookRestController(BookService theBookService) {
		bookService = theBookService;
	}

	@GetMapping("/books/{bookId}")
	public Book getBook(@PathVariable String bookId) {
		int id = Integer.valueOf(bookId);
		Book theBook = bookService.getBook(id);
		if (theBook == null) {
			throw new CustomerNotFoundException("Book not founded for id: " + bookId);
		}
		return theBook;
	}

	@GetMapping("/bytitle/{title}")
	public List<Book> getBooksByTitle(@PathVariable String title) {
		List<Book> theBooks = bookService.getBooksByTitle(title);
		System.out.println("Nece da pridje");
		if (theBooks.size() == 0) {
			throw new CustomerNotFoundException("Book with title " + title + "  not founded !!!");
		}
		return theBooks;
	}

	/*
	 * @PostMapping("/user/books") public Book saveBook(@RequestBody Book theBook) {
	 * theBook.setId(0);
	 * if(theBook.getTitle()==null||theBook.getAutorFirstName()==null||theBook.
	 * getAutorLastName()==null||theBook.getUnitStrength()==0) { throw new
	 * RuntimeException("The Book details are incpomplite!!") ; }
	 * bookService.saveBook(theBook);
	 * 
	 * return theBook; }
	 */
	@PutMapping("/save")
	public Book updateBook(@RequestBody Book theBook) {
		bookService.saveBook(theBook);
		return theBook;
	}

	@DeleteMapping("/books/{bookId}")
	public Book deleteBook(@PathVariable String title) {
		int id = Integer.valueOf(title);
		Book theBook = bookService.getBook(id);
		if (theBook == null) {
			throw new CustomerNotFoundException("Book not founded for id: " + id);
		} else {
			bookService.deleteBook(id);
		}
		return theBook;
	}

	@GetMapping("/byautor/{lastName}")
	public List<Book> getBooksByAutor(@PathVariable String lastName) {
		List<Book> theBooks = bookService.getBooksByAutor(lastName);
		if (theBooks.size() == 0) {
			throw new CustomerNotFoundException("Book writen by " + lastName + "  not founded !!!");
		}
		return theBooks;
	}
}