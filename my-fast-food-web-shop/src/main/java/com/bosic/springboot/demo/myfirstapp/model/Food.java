package com.bosic.springboot.demo.myfirstapp.model;

import java.util.List;

public class Food {
private String name;
private String size;
private String price;
private List<String> adds;

public Food() {
	
}



public Food(String name, String size, String price, List<String> adds) {
	super();
	this.name = name;
	this.size = size;
	this.price = price;
	this.adds = adds;
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSize() {
	return size;
}

public void setSize(String size) {
	this.size = size;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public List<String> getAdds() {
	return adds;
}

public void setAdds(List<String> adds) {
	this.adds = adds;
}


}