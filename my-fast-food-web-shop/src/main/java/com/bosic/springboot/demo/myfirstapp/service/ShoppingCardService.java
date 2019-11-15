package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	public void saveCard(List<Product> list, String name) {

		customer = customerService.getCustomerByName(name);

		listOfCards.add(new ShoppingCard(counter++, list, customer, new Date(), getTotal(list)));
		

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

	public List<ShoppingCard> getCardsForDate(Date date) {

		List<ShoppingCard> dailyCards = listOfCards.stream()
				// filter.(temp->temp.getDate().before(date))
				.collect(Collectors.toList());

		return dailyCards;
	}

	public double getDailyTotal(Date date) {
		List<ShoppingCard> cards = getCardsForDate(date);

		double total = 0;

		for (ShoppingCard card : cards) {

			total = total + card.getTotal();

		}

		return total;
	}

	public long howManyPizzasForDate(Date date) {
		long count = 0;
		long all = 0;
		List<ShoppingCard> dailyCards = getCardsForDate(date);
		for (ShoppingCard dailyCard : dailyCards) {

			System.out.println("ShoppingCard number: " + dailyCard.getId());
			List<Product> dailyProducts = dailyCard.getProductList();
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

}
