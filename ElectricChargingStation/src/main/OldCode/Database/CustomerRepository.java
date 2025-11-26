package org.example.Database;

import org.example.Database.Entities.Customer;

import java.util.HashMap;

public class CustomerRepository {
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        int newId = customers.size() + 1;
        customer.setId(newId);
        customers.put(newId, customer);
    }

}
