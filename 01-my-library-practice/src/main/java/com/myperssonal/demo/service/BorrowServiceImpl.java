package com.myperssonal.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myperssonal.demo.DAO.BorrowBookDAO;
import com.myperssonal.demo.entity.Book;
import com.myperssonal.demo.entity.BorrowBook;
import com.myperssonal.demo.entity.Customer;

@Service
public class BorrowServiceImpl implements BorrowService {
    private List<BorrowBook> borrowBookDAOList;
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BorrowBookDAO borrowBookDAO;

    @Override
    @Transactional
    public void borrowBook(BorrowBook theBorrowBook) {
        int theBookId = theBorrowBook.getBookId();
        int theCustomerId = theBorrowBook.getCustomerId();
        Book theBook = bookService.getBook(theBookId);
        Customer theCustomer = customerService.getCustomer(theCustomerId);
        if ((theBook.getUnitStrength() == 0) || (theCustomer.getForReverse() == 3)) {
            throw new RuntimeException("This is imposibile transaction");
        } else {
            theBook.setUnitStrength(theBook.getUnitStrength() - 1);
            theCustomer.setForReverse(theCustomer.getForReverse() + 1);
            bookService.saveBook(theBook);
            customerService.saveCustomer(theCustomer);
            borrowBookDAO.saveBorrowBook(theBorrowBook);
        }
    }

    @Override
    @Transactional
    public void reverseBook(BorrowBook theBorrowBook) {
        boolean find = false;
        int theBookId = theBorrowBook.getBookId();
        int theCustomerId = theBorrowBook.getCustomerId();
        borrowBookDAOList = borrowBookDAO.getBorrowedBooks(theBookId);
        for (BorrowBook borBook : borrowBookDAOList) {
            if (borBook.getBookId() == theBorrowBook.getBookId()
                    && borBook.getCustomerId() == theBorrowBook.getCustomerId()) {
                find = true;
                Book theBook = bookService.getBook(theBookId);
                Customer theCustomer = customerService.getCustomer(theCustomerId);
                theBook.setUnitStrength(theBook.getUnitStrength() + 1);
                theCustomer.setForReverse(theCustomer.getForReverse() - 1);
                bookService.saveBook(theBook);
                customerService.saveCustomer(theCustomer);
                borrowBookDAO.deleteBorrowBook(theBorrowBook);
            }

        }
        if (!find) {
            throw new RuntimeException("Incorect reverse data");
        }
    }

    public boolean isPosibleToBorrow(BorrowBook borrowBook) {
        Book theBook = bookService.getBook(borrowBook.getBookId());
        Customer theCustomer = customerService.getCustomer(borrowBook.getCustomerId());
        if (theBook != null && theCustomer != null && theBook.getUnitStrength() > 0 && theCustomer.getForReverse() < 3)
            return true;
        else
            return false;
    }
}