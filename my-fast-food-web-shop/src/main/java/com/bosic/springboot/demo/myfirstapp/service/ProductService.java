package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Service;



import com.bosic.springboot.demo.myfirstapp.food.Product;



@Service
public class ProductService {
	
	static int counter=1;
private  List<Product> productList=new ArrayList<>();

	
		
		
		
	
public  List<Product> getProductList() {
		return productList;
}
public void addProduct(Product product) {
	product.setId(counter++);
	productList.add(product);
	
	
}
public void deleteProd(int id) {
	 Iterator<Product> iterator = productList.iterator();
     while (iterator.hasNext()) {
         Product prod = iterator.next();
         if (prod.getId() == id) {
             iterator.remove();
            
         }
     }
	
}
public Product getProdById(int id) {
	 Iterator<Product> iterator = productList.iterator();
     while (iterator.hasNext()) {
         Product prod = iterator.next();
         if (prod.getId() == id) {
             return prod;
         }
     }return null;
}
@Override
public String toString() {
	return "ProductService [productList=" + productList + "]";
}

}

