package com.bosic.springboot.demo.myfirstapp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bosic.springboot.demo.myfirstapp.model.Customer;
import com.bosic.springboot.demo.myfirstapp.model.Pizza;
import com.bosic.springboot.demo.myfirstapp.model.Product;
import com.bosic.springboot.demo.myfirstapp.model.ShoppingCard;

@Service
public class ShoppingCardService {
	@Autowired
	private CustomerService customerService;
	private Customer customer = new Customer();
	private static int counter = 0;
	private static List<ShoppingCard> listOfCards = new ArrayList<>();
	Logger logger = LoggerFactory.getLogger(getClass());

	public void addCard(List<Product> list, String name) {
		customer = customerService.getCustomerByName(name);
		listOfCards.add(new ShoppingCard(counter++, list, customer, getCurrentTimeStamp(), getTotal(list)));
		for (ShoppingCard card : listOfCards) {
			logger.info("in addCard method" + card.getProductList().toString());
		}
	}

	public double getTotal(List<Product> list) {
		double total = 0;
		for (Product prod : list) {
			total = total + prod.getPrice();
			double discount = customer.getDiscountLev() * total / 100;
			total = total - discount;
		}
		return total;
	}

	public List<ShoppingCard> getCardsForDate(String date) {// date is in "yyyy-MM-dd" format
		Stream<ShoppingCard> allCards = listOfCards.stream();
		List<ShoppingCard> dailyCards = allCards.filter(card -> card.getDate().equals(date))
				.collect(Collectors.toList());

		return dailyCards;
	}

	public double getDailyTotal(String date) {
		List<ShoppingCard> cards = getCardsForDate(date);
		double total = 0;
		for (ShoppingCard card : cards) {
			total = total + card.getTotal();
		}
		return total;
	}

	public long howManyPizzasForDate(String date) {// date is in "yyyy-MM-dd" format
		for (ShoppingCard card : listOfCards) {
			logger.info("in getCardsForDate method " + card.getProductList().toString());
		}
		List<ShoppingCard> cards = getCardsForDate(date);
		long all = 0;

		for (ShoppingCard card : cards) {
			List<Product> dailyProducts = card.getProductList();
			long count = 0;
			count = dailyProducts.stream().filter(prod -> prod instanceof Pizza).collect(Collectors.counting());
			all = all + count;
		}
		return all;
	}

	public int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		ShoppingCardService.counter = counter;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public static List<ShoppingCard> getListOfCards() {
		return listOfCards;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

}
