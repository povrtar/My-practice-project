package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bosic.springboot.demo.myfirstapp.controller.CustomerNotFoundException;
import com.bosic.springboot.demo.myfirstapp.model.Customer;
import com.bosic.springboot.demo.myfirstapp.model.Pizza;
import com.bosic.springboot.demo.myfirstapp.model.Product;

@Service
public class ProductService {
    @Autowired
    private Environment env;
    @Autowired
    private CustomerService customerService;
    private static Map<Customer, List<Product>> mapCustomerWithList = new ConcurrentHashMap<>();

    public List<? extends Product> getProductList(String name) {
        if (customerService.getCustomerByName(name) == null) {
            throw new CustomerNotFoundException();
        }
        return mapCustomerWithList.get(customerService.getCustomerByName(name));
    }

    public void addProduct(Product requestProduct, String name) throws Exception {
        Customer customer = customerService.getCustomerByName(name);
        List<Product> list = new ArrayList<>();
        if (mapCustomerWithList.containsKey(customer)) {
            list.addAll(mapCustomerWithList.get(customer));
        }
        Product product = requestProduct;
        if (!productIsAvailable(requestProduct.getType())) {
            throw new CustomerNotFoundException();
        } else {
            if (product instanceof Pizza) {
                product.setPrice(getPrice("pizza" + product.getSize()));
            } else {
                product.setPrice(getPrice(product.getType()));
            }
            list.add(product);
            mapCustomerWithList.put(customer, list);
        }
    }

    public void deleteProduct(String type, String name) throws Exception {
        List<Product> productList = new ArrayList<>();
        if (customerService.getCustomerByName(name) == null) {
            throw new CustomerNotFoundException();
        }
        productList.addAll(mapCustomerWithList.get(customerService.getCustomerByName(name)));
        if (!productList.contains(getProductByType(type, productList))) {
            throw new CustomerNotFoundException();
        }
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product prod = iterator.next();
            if (type.equals(prod.getType())) {
                iterator.remove();
            }
            mapCustomerWithList.put(customerService.getCustomerByName(name), productList);
        }
    }

    public Product getProductByType(String type, List<Product> productList) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product prod = iterator.next();
            if (type.equals(prod.getType())) {
                return prod;
            }
        }
        return null;
    }

    public double getPrice(String productName) {
        return (Double.parseDouble(env.getProperty(productName)));
    }

    public boolean productIsAvailable(String productName) {
        return (env.containsProperty(productName));
    }

    public void cleanProductList(String name) {
        if (mapCustomerWithList.containsKey(customerService.getCustomerByName(name))) {
            mapCustomerWithList.get(customerService.getCustomerByName(name))
                               .clear();

        }
    }
}