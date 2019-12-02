package com.bosic.springboot.demo.myfirstapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.mvstore.ConcurrentArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosic.springboot.demo.myfirstapp.model.Customer;
import com.bosic.springboot.demo.myfirstapp.model.Pizza;
import com.bosic.springboot.demo.myfirstapp.model.Product;
import com.bosic.springboot.demo.myfirstapp.model.ShoppingCart;

@Service
public class ShoppingCartService {
    @Autowired
    private CustomerService customerService;
    private Customer customer = new Customer();
    private static int counter = 1;

    private static ConcurrentArrayList<ShoppingCart> listOfShoppingCarts = new ConcurrentArrayList<ShoppingCart>();

    public void addShoppingCart(List<Product> inputList, String name) {
        List<Product> list = new ArrayList<>();
        list.addAll(inputList);
        customer = customerService.getCustomerByName(name);
        ShoppingCart ShoppingCart = new ShoppingCart(list, customer, Calendar.getInstance(), getTotal(list));
        listOfShoppingCarts.add(ShoppingCart);
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

    public List<ShoppingCart> getShoppingCartsForDate(Calendar date) {
        List<ShoppingCart> dailyShoppingCarts = new ArrayList<>();
        Iterator<ShoppingCart> iterator = listOfShoppingCarts.iterator();
        while (iterator.hasNext()) {
            ShoppingCart shoppingCart = iterator.next();
            Calendar cal1 = shoppingCart.getDate();
            if (isSameDay(date, cal1))
                dailyShoppingCarts.add(shoppingCart);
        }
        if (dailyShoppingCarts.isEmpty()) {
            throw new RuntimeException("None  ShopingShoppingCarts on the " + date + " date!");
        }
        return dailyShoppingCarts;
    }

    public BigDecimal getDailyTotal(Calendar date) {
        List<ShoppingCart> shoppingCarts = getShoppingCartsForDate(date);
        BigDecimal total = new BigDecimal("0.00");
        for (ShoppingCart shoppingCart : shoppingCarts) {
            total = total.add(shoppingCart.getTotal());
        }
        return total;
    }

    public long howManyPizzasForDate(Calendar date) {
        List<ShoppingCart> shoppingCarts = getShoppingCartsForDate(date);
        long all = 0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            List<Product> dailyProducts = shoppingCart.getProductList();
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
        ShoppingCartService.counter = counter;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static ConcurrentArrayList<ShoppingCart> getListOfShoppingCarts() {
        return listOfShoppingCarts;
    }

    private boolean isSameDay(Calendar cal2, Calendar cal1) {
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));

    }
}
