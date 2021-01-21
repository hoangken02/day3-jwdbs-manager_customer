package com.kenIT.demo.service;

import com.kenIT.demo.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    List<Customer> findALl();

    void save(Customer customer);

    Customer findById(int id);

    void remove(int id);

    void update(int id, Customer customer);
}
