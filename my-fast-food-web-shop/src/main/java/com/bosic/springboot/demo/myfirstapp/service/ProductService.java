package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bosic.springboot.demo.myfirstapp.controller.ObjectNotFoundException;
import com.bosic.springboot.demo.myfirstapp.model.Customer;
import com.bosic.springboot.demo.myfirstapp.model.Drink;
import com.bosic.springboot.demo.myfirstapp.model.Pizza;
import com.bosic.springboot.demo.myfirstapp.model.Product;

@Service
public class ProductService {
    @Autowired
    Environment env;
    static int counter = 0;
    private double total = 0;
    private Product product = new Product();
    private static List<Product> productList = new ArrayList<>();
    private HashMap<Customer, List<Product>> customerWithList = new HashMap<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product requestProduct) throws ObjectNotFoundException {
        if (!productIsAvailable(requestProduct.getType())) {
            throw ObjectNotFoundException.createWith(requestProduct.getClass()
                                                                   .toString());
        } else {
            product = requestProduct;
            if (product instanceof Pizza)
                product.setPrice(getPrice("pizza" + (product.getSize())));
            if (product instanceof Drink)
                product.setPrice(getPrice(product.getType()));
            product.setId(counter++);
            productList.add(product);
            total = total + product.getPrice();
        }
    }

    public void deleteProduct(int id) throws ObjectNotFoundException {
        if (!(productList.contains(getProductById(id)))) {
            throw ObjectNotFoundException.createWith(Product.class.getName()
                                                                  .toString());
        }
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product prod = iterator.next();
            if (prod.getId() == id) {
                total = total - prod.getPrice();
                iterator.remove();
            }
        }
    }

    public void cleanProductList() {
        productList.clear();
        total = 0;
        counter = 0;
    }

    public Product getProductById(int id) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product prod = iterator.next();
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public double getPrice(String productName) {
        return (Double.parseDouble(env.getProperty(productName)));
    }

    public double getTotal() {
        return total;
    }

    public boolean productIsAvailable(String productName) {
        return (env.containsProperty(productName));
    }

    @Override
    public String toString() {
        return "ProductService [productList=" + productList + "]";
    }
}
