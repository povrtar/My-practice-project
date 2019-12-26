package com.myperssonal.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myperssonal.demo.entity.Book;
import com.myperssonal.demo.entity.Customer;
import com.myperssonal.demo.service.BookService;
import com.myperssonal.demo.service.CustomerService;

@Controller
@RequestMapping("api/service")
public class ServiceController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book theBook) {
        theBook.setId(0);
        if (bookService.isComplete(theBook)) {
            bookService.saveBook(theBook);

        } else {
            throw new RuntimeException("The Book details are incomplete!!");
        }
        return theBook;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book theBook) {
        if (bookService.isComplete(theBook)) {
            bookService.saveBook(theBook);
        } else {
            throw new RuntimeException("The Book details are incomplete!!");
        }
        return theBook;
    }

    @DeleteMapping("/books/{bookId}")
    public Book deleteBook(@PathVariable String title) {
        int id = Integer.valueOf(title);
        Book theBook = bookService.getBook(id);
        if (theBook == null) {
            throw new EntityNotFoundException("Book not founded for id: " + id);
        } else {
            bookService.deleteBook(id);
        }
        return theBook;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        theCustomer.setId(0);
        if (theCustomer.getFirstName() == null || theCustomer.getLastName() == null) {
            throw new RuntimeException("Customer must have first and last name!!!");
        }
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer tempCustomer = customerService.getCustomer(customerId);
        if (tempCustomer == null) {
            throw new EntityNotFoundException("Customer not founded for id: " + customerId);
        }
        customerService.deleteCustomer(customerId);
        return "Deleted customer id-" + customerId;
    }

}
