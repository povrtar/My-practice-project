package com.myperssonal.demo.DAO;

import java.util.List;

import com.myperssonal.demo.entity.BorrowBook;

public interface BorrowBookDAO {
public void saveBorrowBook(BorrowBook bb);
public BorrowBook getBorrowBook(int id);
public String deleteBorrowBook(BorrowBook bb);
public List<BorrowBook> getBorrowedBooks(int id);
}
