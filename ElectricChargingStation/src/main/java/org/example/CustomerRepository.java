package org.example;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    public HashMap<Integer, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        /*int newId = customers.size() + 1;
        customer.setId(newId);
        customers.put(newId, customer);*/

        customers.put(customer.customerId, customer);
    }
}
