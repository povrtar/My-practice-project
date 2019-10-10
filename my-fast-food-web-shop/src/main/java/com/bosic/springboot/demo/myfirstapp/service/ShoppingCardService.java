package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosic.springboot.demo.myfirstapp.entity.Customer;
import com.bosic.springboot.demo.myfirstapp.food.Product;
import com.bosic.springboot.demo.myfirstapp.food.ShoppingCard;
@Service
public class ShoppingCardService {
	@Autowired
	private CustomerService  customerService ;
	Customer customer=new Customer();
	private static int counter=0;
	
private static List<ShoppingCard> listOfCards=new ArrayList<>();
public void saveCard(List<Product> list,String name) {
	System.out.println("prosao name"+name);
	customer=customerService.getCustomerByName(name);
	System.out.println("Customer is:"+customer.getFirstName().toString()+" "+customer.getLastName().toString()+" "+customer.getPassword().toString());
	listOfCards.add(new ShoppingCard(counter++,list,customer,new Date(),getTotal(list)));
System.out.println(counter);
System.out.println(customerService.getCustomerByName(name).getFirstName());

for (Product prod:list) {
	System.out.println(prod.getType().toString()+" "+prod.getPrice());
}
System.out.println(getTotal(list));
}
public double getTotal(List<Product> list) {
	double total=0;
	for(Product prod:list) {
		total=total+prod.getPrice();
		double discount=customer.getDiscountLev()*total/100;
		System.out.println("Total without a dicount"+total);
		total=total-discount;
		System.out.println("Discount is : "+discount);
		
	}
	return total;
}

}
