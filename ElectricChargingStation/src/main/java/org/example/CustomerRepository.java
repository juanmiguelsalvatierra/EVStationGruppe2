package org.example;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private HashMap<Integer, Customer> customers = new HashMap<>();
    public int customerId = 0;

    public void save(Customer customer) {
        customer.setId(customerId++);
        customers.put(customerId, customer);
    }

}
