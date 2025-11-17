package org.example;

import java.util.HashMap;

public class CustomerRepository {
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        int newId = customers.size() + 1;
        customer.setId(newId);
        customers.put(newId, customer);
    }

}
