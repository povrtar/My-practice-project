package com.bosic.springboot.demo.myfirstapp.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import com.bosic.springboot.demo.myfirstapp.entity.Customer;

public class ShoppingCard {
	
	private static int id=1;
	private List<Product> productList=new ArrayList<>();
	private Customer customer=new Customer();
	Date date=new Date();
	private Double total;
	
	public ShoppingCard(int id,List<Product> productList, Customer customer, Date date, Double total) {
		super();
	
		this.productList = productList;
		this.customer = customer;
		this.date = date;
		this.total = total;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		ShoppingCard.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ShoppingCard [productList=" + productList + ", customer=" + customer + ", date=" + date + ", total="
				+ total + "]";
	}
	
}
