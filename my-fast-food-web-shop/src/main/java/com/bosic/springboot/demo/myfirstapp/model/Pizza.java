package com.bosic.springboot.demo.myfirstapp.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Pizza extends Product {

    Logger logger=LoggerFactory.getLogger(getClass());
	public Pizza() {
	}

	public Pizza(int id, String type, String size) {
		super(id, type, size);
		// TODO Auto-generated constructor stub
	}

	public Pizza(int id, String type, String size, String type2, String size2, double price) {
		super(id, type, size);
		type = type2;
		size = size2;
		this.price = price;
	}

	private String type;
	private String size;
	private double price;

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Pizza [type=" + type + ", size=" + size + "]";
	}

}
