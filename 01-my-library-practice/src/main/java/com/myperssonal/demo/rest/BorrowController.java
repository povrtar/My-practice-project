package com.myperssonal.demo.rest;

import java.sql.Date;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.BorrowBook;
import com.myperssonal.demo.service.BorrowService;


@RestController
@RequestMapping("/api")
public class BorrowController {
 BorrowService borrowService;
 @Autowired
 public BorrowController(BorrowService theBorrowService) {
	 borrowService=theBorrowService;
 }
@PostMapping("/borrow")
	public void borrowBook(@RequestBody BorrowBook theBorrow) {
	if(theBorrow.getBookId()==0||theBorrow.getCustomerId()==0) {
		throw new RuntimeException("Inputed parameters can't be 0 !!!");
	}
	long date= ZonedDateTime.now().toInstant().toEpochMilli();
	theBorrow.setDate(date/1000/60/60/24);
	borrowService.borrowBook(theBorrow);
}
@DeleteMapping("/borrow")
public void reverseBook(@RequestBody BorrowBook bb) {
	
	borrowService.reverseBook(bb);
	
}
/*@GetMapping("/borrow/all")
public void reverseBooks(@RequestBody BorrowBook bb) {
	
	borrowService.reverseBook(bb);
	
}*/
}
