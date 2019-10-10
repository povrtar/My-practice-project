package com.myperssonal.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_customer")
public class BorrowBook {
@Id
@Column(name="book_id")
private int bookId;
@Column(name="customer_id")
private int customerId;
@Column(name="date")
private long date;
public BorrowBook() {
	
}
public BorrowBook(int bookId,int customerId) {
	
}
public BorrowBook(int bookId, int customerId, long date) {
	super();
	this.bookId = bookId;
	this.customerId = customerId;
	this.date = date;
}

public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public long getDate() {
	return date;
}
public void setDate(long date) {
	this.date = date;
}

}
