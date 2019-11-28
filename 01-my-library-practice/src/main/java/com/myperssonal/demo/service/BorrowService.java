package com.myperssonal.demo.service;

import com.myperssonal.demo.entity.BorrowBook;

public interface BorrowService {

    public void borrowBook(BorrowBook theBorrowBook);

    public void reverseBook(BorrowBook theBorrowBook);

    public boolean isPosibleToBorrow(BorrowBook theBorrow);
}
