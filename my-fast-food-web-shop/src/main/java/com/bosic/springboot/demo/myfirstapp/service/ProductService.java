package com.bosic.springboot.demo.myfirstapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    CustomerService customerService;
    private static int counter = 0;
    private static Map<Customer, List<Product>> mapCustomerWithList = new ConcurrentHashMap<>();

    public List<? extends Product> getProductList(String name) {
        if (customerService.getCustomerByName(name) == null) {
            throw ObjectNotFoundException.createWith(Customer.class.getName()
                                                                   .toString());
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
            throw ObjectNotFoundException.createWith(requestProduct.getClass()
                                                                   .toString());
        } else {
            product = requestProduct;
            if (product instanceof Pizza) {
                product.setPrice(getPrice("pizza" + (product.getSize())));
            }
            if (product instanceof Drink) {
                product.setPrice(getPrice(product.getType()));
            }
            product.setId(counter++);
            list.add(product);
            mapCustomerWithList.put(customer, list);
        }
    }

    public void deleteProduct(int id, String name) throws Exception {
        List<Product> productList = new ArrayList<>();
        if (customerService.getCustomerByName(name) == null) {
            throw ObjectNotFoundException.createWith(Customer.class.getName()
                                                                   .toString());
        }
        productList.addAll(mapCustomerWithList.get(customerService.getCustomerByName(name)));
        if (!(productList.contains(getProductById(id, productList)))) {
            throw ObjectNotFoundException.createWith(Product.class.getName()
                                                                  .toString());
        }
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product prod = iterator.next();
            if (prod.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Product getProductById(int id, List<Product> productList) {
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
