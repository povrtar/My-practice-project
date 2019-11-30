package com.bosic.springboot.demo.myfirstapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.mvstore.ConcurrentArrayList;
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
    private static int counter = 1;

    private static ConcurrentArrayList<ShoppingCard> listOfCards = new ConcurrentArrayList<ShoppingCard>();
    Logger logger = LoggerFactory.getLogger(getClass());

    public void addCard(List<Product> inputList, String name) {
        List<Product> list = new ArrayList<>();
        list.addAll(inputList);
        customer = customerService.getCustomerByName(name);
        ShoppingCard card = new ShoppingCard(list, customer, Calendar.getInstance(), getTotal(list));
        listOfCards.add(card);
    }

    public BigDecimal getTotal(List<Product> list) {
        BigDecimal total = new BigDecimal("0.00");
        for (Product prod : list) {
            total = total.add(new BigDecimal(prod.getPrice()));
            BigDecimal discount = new BigDecimal(customer.getDiscountLev()).multiply(
                    total.divide(new BigDecimal("100")));
            total = total.subtract(discount);
        }
        return total;
    }

    public List<ShoppingCard> getCardsForDate(Calendar date) {
        List<ShoppingCard> dailyCards = new ArrayList<>();
        Iterator<ShoppingCard> iterator = listOfCards.iterator();
        while (iterator.hasNext()) {
            ShoppingCard card = iterator.next();
            Calendar cal1 = card.getDate();
            if (isSameDay(date, cal1))
                dailyCards.add(card);
        }
        if (dailyCards.isEmpty())
            throw new RuntimeException("None  ShopingCards on the " + date + " date!");
        return dailyCards;
    }

    public BigDecimal getDailyTotal(Calendar date) {
        List<ShoppingCard> cards = getCardsForDate(date);
        BigDecimal total = new BigDecimal("0.00");
        for (ShoppingCard card : cards) {
            total = total.add(card.getTotal());
        }
        return total;
    }

    public long howManyPizzasForDate(Calendar date) {
        List<ShoppingCard> cards = getCardsForDate(date);
        logger.info("cards= " + cards.toString());
        long all = 0;
        for (ShoppingCard card : cards) {
            List<Product> dailyProducts = card.getProductList();
            long count = 0;
            count = dailyProducts.stream()
                                 .filter(prod -> prod instanceof Pizza)
                                 .collect(Collectors.counting());
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

    public static ConcurrentArrayList<ShoppingCard> getListOfCards() {
        return listOfCards;
    }

    private boolean isSameDay(Calendar cal2, Calendar cal1) {

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));

    }
}
